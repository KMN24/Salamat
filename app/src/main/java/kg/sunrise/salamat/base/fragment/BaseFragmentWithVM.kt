package kg.sunrise.salamat.base.fragment

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.wang.avi.AVLoadingIndicatorView
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.state.State
import kg.sunrise.salamat.utils.extesions.gone
import kg.sunrise.salamat.utils.extesions.toast
import kg.sunrise.salamat.utils.extesions.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

abstract class BaseFragmentWithVM<Binding: ViewBinding, out VM : BaseViewModel> : BaseFragment<Binding>() {
    protected abstract val viewModel : VM
    protected abstract val progressBar : ConstraintLayout

    companion object {
        const val DEFAULT_INTEGER = 1
        const val DEFAULT_NO_INTERNER_INTEGER = -1
        const val DEFAULT_ERROR_INT = 301
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            if (state == null) {
                Timber.e("Item class is empty")
            } else {
                when (state) {
                    is State.LoadingState -> {
                        when {
                            state.isLoading -> progressBar.visible()
                            else -> progressBar.gone()
                        }
                    }

                    is State.ErrorState -> {
                        handleError(state)
                    }
                    is State.NoItemState -> {
                        successRequest()
                    }

                    is State.SuccessObjectState<*> -> {
                        findTypeOfObject(state.data)
                    }
                    else -> {

                    }
                }
            }
        })
    }

    abstract fun findTypeOfObject(data: Any?)

    abstract fun successRequest()

    private fun handleError(state: State.ErrorState) {
        Timber.e("err ${state.message}")
//        FirebaseCrashlytics.getInstance().also {
//            it.setCustomKey(state.errorCode.toString(), state.message)
//            it.log(state.message)
//            it.recordException(Throwable(state.errorCode.toString()))
//        }.sendUnsentReports()

        when (state.errorCode) {
            DEFAULT_INTEGER -> requireContext().toast(getString(R.string.error_network))
            DEFAULT_NO_INTERNER_INTEGER -> requireContext().toast(getString(R.string.error_no_internet))
            DEFAULT_ERROR_INT -> showErrorAsDialog(state.message)
            401 -> CoroutineScope(Dispatchers.IO).launch {
//                FCMTokenUtils.getFCMToken()
//                SharedPrefModule(this@BaseActivity).TokenModule().deleteToken()
//                withContext(Dispatchers.Main) {
//                    startActivity(
//                        Intent(this@BaseActivity, LoginActivity::class.java).setFlags(
//                            Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        )
//                    )
//                }
            }
            500 -> showErrorAsDialog(getString(R.string.error_server))
            404 -> showErrorAsDialog(getString(R.string.error_not_found))
            413 -> showErrorAsDialog(getString(R.string.error_big_file))
            else -> {

                try {
                    val jsonObject = JSONObject(state.message)

                    when {
                        jsonObject.has("detail") -> try {
                            val message = jsonObject.getString("detail")
                            showErrorAsDialog(message)
                        } catch (e: Exception) {
                            val jsonArray = jsonObject.getJSONArray("detial")
                            if (jsonArray.length() > 0) {
                                showErrorAsDialog(jsonArray.getString(1))
                            }
                        }
                        jsonObject.has("message") -> try {
                            val message = jsonObject.getString("message")
                            showErrorAsDialog(message)
                        } catch (e: Exception) {
                            val jsonArray = jsonObject.getJSONArray("message")
                            if (jsonArray.length() > 0) {
                                showErrorAsDialog(jsonArray.getString(1))
                            }
                        }
                        else -> handleCustomError(state.message)
                    }
                } catch (e: Exception) {
                    handleCustomError(state.message)
                }
            }
        }
    }

    protected open fun handleCustomError(message: String) {
        showErrorAsDialog(message)
    }

    private fun showErrorAsDialog(message: String) {
        toast(message)
    }
}