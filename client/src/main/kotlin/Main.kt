import kotlinx.browser.document
import react.Props
import react.create
import react.dom.html.ReactHTML.p
import react.dom.render
import react.fc

fun main() {
    document.getElementById("root")?.let {
        render(app.create(), it)
    }
}

val app = fc<Props> {
    p { +"Hello from React" }
}