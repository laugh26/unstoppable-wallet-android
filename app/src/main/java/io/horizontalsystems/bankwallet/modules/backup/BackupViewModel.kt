package io.horizontalsystems.bankwallet.modules.backup

import androidx.lifecycle.ViewModel
import io.horizontalsystems.bankwallet.entities.Account
import io.horizontalsystems.core.SingleLiveEvent

class BackupViewModel : ViewModel(), BackupModule.View, BackupModule.Router {

    lateinit var delegate: BackupModule.ViewDelegate

    val startPinModule = SingleLiveEvent<Void>()
    val startBackupWordsModule = SingleLiveEvent<Pair<List<String>, Int>>()
    val startBackupEosModule = SingleLiveEvent<Pair<String, String>>()
    val closeLiveEvent = SingleLiveEvent<Void>()
    val showSuccessAndFinishEvent = SingleLiveEvent<Void>()

    fun init(account: Account) {
        BackupModule.init(this, this, account)
    }

    // router

    override fun showSuccessAndFinish() {
        showSuccessAndFinishEvent.call()
    }

    override fun startUnlockPinModule() {
        startPinModule.call()
    }

    override fun startBackupWordsModule(words: List<String>, accountTypeTitle: Int) {
        startBackupWordsModule.postValue(Pair(words, accountTypeTitle))
    }

    override fun startBackupEosModule(account: String, activePrivateKey: String) {
        startBackupEosModule.postValue(Pair(account, activePrivateKey))
    }

    override fun close() {
        closeLiveEvent.call()
    }
}
