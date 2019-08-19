package com.upadhyay.exoplayertest.ui.fragment

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.upadhyay.exoplayertest.R
import com.upadhyay.exoplayertest.databinding.FragmentPlayerBinding
import com.upadhyay.exoplayertest.utils.Constants

class PlayerFragment : AbstractBaseDataBindingFragment<FragmentPlayerBinding>(),
    Player.EventListener {

    private var mPlayer: SimpleExoPlayer? = null

    private var playbackPosition: Long = 0L
    private var currentWindow: Int = 0
    private var mPlayWhenReady: Boolean = false

    companion object {
        fun getInstance() = PlayerFragment()
    }


    override fun getLayout(): Int = R.layout.fragment_player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayer()
    }

    override fun onResume() {
        super.onResume()
        loadState()
    }

    override fun onPause() {
        super.onPause()
        saveState()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun loadState() {
        mPlayer?.apply {
            playWhenReady = true
            seekTo(currentWindowIndex, playbackPosition)
            addListener(this@PlayerFragment)
        }
    }

    private fun initializePlayer() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(context),
            DefaultTrackSelector(),
            DefaultLoadControl()
        )
        binding.playerView.player = mPlayer

        val mediaSource = buildMediaSource(Uri.parse(getString(R.string.stream_url)))
        mPlayer?.prepare(mediaSource, true, false)
    }

    private fun buildMediaSource(uri: Uri): MediaSource =
        HlsMediaSource.Factory(DefaultHttpDataSourceFactory(Constants.AGENT_PROVIDER))
            .createMediaSource(uri)


    private fun releasePlayer() {
        mPlayer?.release()
        mPlayer = null
    }

    private fun saveState() {
        playbackPosition = mPlayer?.currentPosition ?: 0L
        currentWindow = mPlayer?.currentWindowIndex ?: 0
        mPlayWhenReady = mPlayer?.playWhenReady ?: true
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        val currentOrientation = resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE)
            onFullScreen()
        else
            onRestoreScreen()
    }

    private fun onFullScreen() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun onRestoreScreen() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_READY ->
                progressBarUpdate(false)
            Player.STATE_BUFFERING ->
                progressBarUpdate(show = true)
        }
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
        // need to check
    }

    override fun onSeekProcessed() {
        // need to check
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
        // need to check
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        // need to check
    }

    override fun onLoadingChanged(isLoading: Boolean) {
        progressBarUpdate(isLoading)
    }

    override fun onPositionDiscontinuity(reason: Int) {
        // need to check
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        // need to check
    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
        // need to check
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
        // need to check
    }

    private fun progressBarUpdate(show: Boolean) {
        if (show) {
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.pbLoading.visibility = View.GONE
        }

    }
}