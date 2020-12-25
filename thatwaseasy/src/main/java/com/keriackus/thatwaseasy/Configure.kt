package com.keriackus.thatwaseasy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Configure {
  internal val italianJob = CoroutineScope(Dispatchers.IO).launch {  }
}