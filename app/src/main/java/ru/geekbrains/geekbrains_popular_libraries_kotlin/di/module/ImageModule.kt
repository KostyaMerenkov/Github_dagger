package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import android.content.Context
import android.widget.ImageView
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.room.RoomImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image.GlideImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.network.AndroidNetworkStatus
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> =
        GlideImageLoader(cache, networkStatus)

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") dir: File): IImageCache =
        RoomImageCache(database, dir)

    @Singleton
    @Provides
    fun networkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)

    @Singleton
    @Named("cacheDir")
    @Provides
    fun dir(app: App): File = app.cacheDir

}