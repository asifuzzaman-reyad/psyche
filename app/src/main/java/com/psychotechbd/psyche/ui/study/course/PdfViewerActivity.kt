package com.psychotechbd.psyche.ui.study.course

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.krishna.fileloader.FileLoader
import com.krishna.fileloader.listener.FileRequestListener
import com.krishna.fileloader.pojo.FileResponse
import com.krishna.fileloader.request.FileLoadRequest
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_pdf_viewer.*
import java.io.File

class PdfViewerActivity : AppCompatActivity() {

    private var i: Int = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pdf_viewer)

        val lessonNo: String? = intent.getStringExtra("lesson")
        supportActionBar?.title = lessonNo

        val intent: String? = intent.getStringExtra("pdfLink")
        Log.i("link", intent!!)

        loadPdf(intent)
        loadProgressBar()
    }

    private fun loadPdf(url: String?) {
        FileLoader.with(applicationContext)
            .load(url, false)
            .fromDirectory("pdfFile", FileLoader.DIR_INTERNAL)
            .asFile(object : FileRequestListener<File> {
                override fun onLoad(
                    request: FileLoadRequest?,
                    response: FileResponse<File>?
                ) {
                    progressBar_pdf_viewer.visibility = View.GONE    //hide progress bar
                    pdf_progress_tv.visibility = View.GONE

                    val pdfFile: File? = response?.body

                    //load pdf from internet
                    git_pdfView.fromFile(pdfFile)
                        .defaultPage(0)
                        .enableDoubletap(true)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .onDraw { canvas, pageWidth, pageHeight, displayedPage -> }
                        .onPageChange { page, pageCount -> }
                        .onPageError { page, t ->
                            Toast.makeText(
                                applicationContext, "Error while opening page",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            Log.d("Error", "" + t.localizedMessage)
                        }
                        .onTap { false }
                        .onRender { nbPages, pageWidth, pageHeight ->
                            //fit to screen size
                            git_pdfView.fitToWidth()
                        }
                        .enableAnnotationRendering(true)
                        .invalidPageColor(Color.RED)
                        .load()
                }

                override fun onError(request: FileLoadRequest?, t: Throwable?) {
                    Toast.makeText(applicationContext, "" + t?.message, Toast.LENGTH_SHORT).show()

                    progressBar_pdf_viewer.visibility = View.GONE   //hide progress bar
                    pdf_progress_tv.visibility = View.GONE
                }
            })
    }

    @SuppressLint("SetTextI18n")
    fun loadProgressBar() {
        progressBar_pdf_viewer.visibility = View.VISIBLE
        pdf_progress_tv.visibility = View.VISIBLE

        i = progressBar_pdf_viewer!!.progress

        Thread {
            while (i < 100) {
                i += 4
                // Update the progress bar and display the current value
                handler.post {
                    progressBar_pdf_viewer!!.progress = i
                    pdf_progress_tv!!.text = i.toString() + "/" + progressBar_pdf_viewer!!.max
                }
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}