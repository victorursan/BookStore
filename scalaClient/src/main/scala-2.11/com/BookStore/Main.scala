package com.BookStore

import com.BookStore.ui.Console
import com.caucho.hessian.client.HessianProxyFactory

/**
  * Created by victor on 4/20/16.
  */

object Main extends App {
  override def main(args: Array[String]) {
    val url = "http://localhost:8080/hessian/books"
    val factory: HessianProxyFactory = new HessianProxyFactory
    val controllerService = factory.create(url).asInstanceOf[ControllerService]
    new Console(controllerService).run()
  }

}
