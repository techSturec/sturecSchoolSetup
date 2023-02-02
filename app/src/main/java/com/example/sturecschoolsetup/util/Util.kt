package com.example.sturecschoolsetup.util

import android.widget.EditText

class Util {
    fun makeHashMap(list:List< Pair<String, EditText> > ):HashMap<String, Any> {
        val map = HashMap<String, Any>()
        for(i in list)
        {
            map[i.first] = i.second.text.toString()
        }
        return map
    }
}