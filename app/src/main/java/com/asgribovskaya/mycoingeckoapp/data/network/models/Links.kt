package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("homepage")
    val homepage: List<String>,
    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,
    @SerializedName("official_forum_url")
    val officialForumUrl: List<String>,
    @SerializedName("chat_url")
    val chatUrl: List<String>,
    @SerializedName("announcement_url")
    val announcementUrl: List<String>,
    @SerializedName("twitter_screen_name")
    val twitterScreenName: String,
    @SerializedName("facebook_username")
    val facebookUsername: String,
    @SerializedName("bitcointalk_thread_identifier")
    val bitcointalkThreadIdentifier: Any?,
    @SerializedName("telegram_channel_identifier")
    val telegramChannelIdentifier: String,
    @SerializedName("subreddit_url")
    val subredditUrl: String,
    @SerializedName("repos_url")
    val reposUrl: ReposUrl
)