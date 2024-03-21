package com.plcoding.composepaging3caching.di


import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.plcoding.composepaging3caching.data.local.BeerDao
import com.plcoding.composepaging3caching.data.local.BeerDataBase
import com.plcoding.composepaging3caching.data.local.BeerEntity
import com.plcoding.composepaging3caching.data.remote.BeerApi
import com.plcoding.composepaging3caching.data.remote.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Provides
        @Singleton

        fun provideBeerDatabase(@ApplicationContext context: Context): BeerDataBase {
            return Room.databaseBuilder(
                context,
                BeerDataBase::class.java,
                "beers.db"
            ).build()
        }

        @Provides
        @Singleton
        fun provideBeerApi(): BeerApi {
            return Retrofit.Builder()
                .baseUrl(BeerApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create()
        }

        @OptIn(ExperimentalPagingApi::class)
        @Provides
        @Singleton
        fun provideBeerPager(beerDb: BeerDataBase, beerApi: BeerApi): Pager<Int, BeerEntity> {
            return Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = BeerRemoteMediator(
                    beerDb = beerDb,
                    beerApi = beerApi
                ),
                pagingSourceFactory = {
                    beerDb.dao.pagingSource()
                }
            )
        }
    }
