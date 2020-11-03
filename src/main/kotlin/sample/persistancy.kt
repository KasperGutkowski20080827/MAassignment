package sample

import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class persistancy {
    fun write(fileName: String, data: String){
        val file = File(fileName)
        try {
            val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        }catch (e: Exception){
            println("ERROR")
        }
    }

    fun read(fileName: String): String{
        val file = File(fileName)
        var str = ""
        try {
            val inputStreamReader = InputStreamReader(FileInputStream(file))
            if(inputStreamReader != null){
                val bufferedReader = BufferedReader(inputStreamReader)
                val partialStr = StringBuilder()
                var done = false
                while(!done){
                    var line = bufferedReader.readLine()
                    done = (line == null)
                    if (line != null) partialStr.append(line)
                }
                inputStreamReader.close()
                str = partialStr.toString()
            }
        }catch (e: FileNotFoundException){
            print("No file found")
        }catch (e: IOException){
            print("ERROR")
        }
        return str
    }

    fun exists(fileName: String):Boolean{
        val file = File(fileName)
        return file.exists()
    }
}