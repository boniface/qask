import conf.CORSFilter
import play.api.GlobalSettings
import play.api.mvc.WithFilters

/**
 * Created by hashcode on 2014/07/19.
 */
object Global extends WithFilters(CORSFilter()) with GlobalSettings  {

}
