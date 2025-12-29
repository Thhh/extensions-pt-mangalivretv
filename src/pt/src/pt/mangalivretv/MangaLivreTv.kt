package eu.kanade.tachiyomi.extension.pt.mangalivretv

import eu.kanade.tachiyomi.multisrc.mangabox.MangaBox
import eu.kanade.tachiyomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import okhttp3.Request

class MangaLivreTv : MangaBox("MangaLivre.tv", "https://mangalivre.tv", "pt-BR") {

    private val desktopUA =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2)
        .addInterceptor { chain ->
            val req: Request = chain.request().newBuilder()
                .header("User-Agent", desktopUA)
                .header("Accept-Language", "pt-BR,pt;q=0.9")
                .header("Referer", baseUrl)
                .build()
            chain.proceed(req)
        }
        .build()
}
