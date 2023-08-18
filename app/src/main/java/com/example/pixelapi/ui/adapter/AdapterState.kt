package com.example.pixelapi.ui.adapter


sealed class AdapterState {
    object Idle:AdapterState()
    class Removed(val index1:Int, val index2:Int):AdapterState()
    class Changed(val index:Int):AdapterState()
    class ChangedAll(val index1:Int, val index2:Int):AdapterState()
}