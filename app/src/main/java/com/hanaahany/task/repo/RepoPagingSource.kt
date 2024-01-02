import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hanaahany.task.model.response.allrepo.AllRepoResponseItem
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.repo.Repo
import com.hanaahany.task.repo.RepoImp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull


//class GithubPagingSource(private val githubRepository: Repo) : PagingSource<Int, AllRepoItem>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllRepoItem> {
//        val position = params.key ?: 1
//
//        return try {
//            val response = githubRepository.getAllRepo(1)
//                val data = response.body() ?: emptyList()
//                val prevKey = if (position == 1) null else position - 1
//                val nextKey = if (data.isEmpty()) null else position + 1
//                LoadResult.Page(data, prevKey, nextKey)
//            } else {
//                LoadResult.Error(Exception("Network error"))
//            }
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, AllRepoItem>): Int? {
//        TODO("Not yet implemented")
//    }
//}