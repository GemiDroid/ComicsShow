import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gemidroid.comicshow.data.datasource.localDB.IComicDAO
import com.gemidroid.comicshow.data.models.Comic

@Database(entities = [Comic::class], version = 1)
abstract class ComicDB : RoomDatabase() {

    abstract fun comicDao(): IComicDAO

    companion object {
        @Volatile
        private var instance: ComicDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ComicDB::class.java, "comic.db")
            .build()
    }
}
