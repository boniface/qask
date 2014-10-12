package controllers

import domain.{NormalUser, Administrator, Permission, Account}
import jp.t2v.lab.play2.auth.AuthConfig
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

/**
 * Created by hashcode on 2014/10/11.
 */
trait AuthConfigImpl extends AuthConfig {


}