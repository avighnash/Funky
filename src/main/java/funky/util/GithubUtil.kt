package funky.util


import com.jcabi.github.Github
import com.jcabi.github.RtGithub
import com.jcabi.http.response.JsonResponse
import javax.json.JsonObject

object GithubUtil {

    fun searchForRepo(input: String): MutableList<JsonObject> {

        val github: Github = RtGithub()
        val resp: JsonResponse = github.entry()
                .uri().path("/search/repositories")
                .queryParam("q", input).back()
                .fetch()
                .`as`(JsonResponse::class.java)

        val items = resp.json().readObject()
                .getJsonArray("items")
                .getValuesAs(JsonObject::class.java)

        return items
    }
}