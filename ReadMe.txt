NewsApp

Aplicación donde puede encontrar las noticias mas relevantes acerca del pais (Colombia)

In this Application you can find the most relevant News of Country (Colombia)

Architecture Components
- Navigation Component
- ViewModel, LiveData
- Room Database

Asynchronous Calls
- Coroutines

API REST
- Retrofit

Images
- Glide

News API
https://newsapi.org/


****************************
NOTES ABOUT PROJECT:
****************************

Retrofit request

- @GET("v2/top-headlines") -> is for main News

See documentation -> https://newsapi.org/docs/endpoints/top-headlines

- @GET("v2/everything") -> Is to search news of everything about specific search.

https://newsapi.org/docs/endpoints/everything


**********************************
// Insert an article and return the id of this insert.

interface ArticleDao {
	    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long 
    
}

*************************************

In database Room just use primitive kind of data
(Int, String...) -> Article::class
If we have another Class -> Source::class
we have to convert Source class to String to be capable to use in Room.


TypeConverted	

data class Article(
	val author: String,
	val description: String,
	val source: Source
)

data class Source {
	val id: Int,
	val name: String
}

// I only want to get name.
    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }


 // To get all data (id and name) need to use Gson to convert into Json String


        @TypeConverter
        fun fromProfileData(source: Source): String {
            return Gson().toJson(source)
        }

        @TypeConverter
        fun toProfileData(sourceString: String?): Source) {
            return Gson().fromJson(sourceString, Source::class.java)
        }

Finally add to ArticleDatabse (entities = [Article::class], version = 1) 
@TypeConverters(Converters::class)
abstact class ....{}

**************************

DiffUtil is to impove performance of recyclerview
compare the oldList with newList to avoid update all List
just update what is needed, what was changed on the List. 

    private val differCallback = object : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            TODO("Not yet implemented")
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

***********************************

SetOnClickListener in the Adapter without interface -> using Lamba

    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnClickListener(listener : (Article) -> Unit) {
        onItemClickListener = listener
    }

    // This is in the bin funtion in inner classViewHolder.
    setOnClickListener {
        onItemClickListener?.let {
            it(article)
        }
    }

    