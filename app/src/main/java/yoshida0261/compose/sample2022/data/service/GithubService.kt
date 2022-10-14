package yoshida0261.compose.sample2022.data.service

import yoshida0261.compose.sample2022.data.model.Contributers
import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    //curl \
    //  -H "Accept: application/vnd.github+json" \
    //  -H "Authorization: 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" \
    //  https://api.github.com/repos/DroidKaigi/conference-app-2022
    ///stats/contributors
    @GET("repos/DroidKaigi/conference-app-2022")
    suspend fun getContributers(): Response<Contributers>
}
