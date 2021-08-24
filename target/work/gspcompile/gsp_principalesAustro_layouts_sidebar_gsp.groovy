import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_layouts_sidebar_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_sidebar.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (actionName == "dashboard")) {
printHtmlPart(1)
}
printHtmlPart(2)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(3)
if(true && (Util.checkAccess(session.user.usuario, '/permiso/index') || Util.checkAccess(session.user.usuario, '/rol/index') || Util.checkAccess(session.user.usuario, '/usuario/index'))) {
printHtmlPart(4)
if(true && (Util.checkAccess(session.user.usuario, '/permiso/index'))) {
printHtmlPart(5)
if(true && (controllerName == "permiso")) {
printHtmlPart(6)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/permiso/'))
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/rol/index'))) {
printHtmlPart(5)
if(true && (controllerName == "rol")) {
printHtmlPart(10)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/rol/'))
printHtmlPart(11)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/usuario/index'))) {
printHtmlPart(5)
if(true && (controllerName == "usuario" && actionName != "dashboard")) {
printHtmlPart(12)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/usuario/'))
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (controllerName == "gestion" && actionName == "cargarBase")) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/gestion/cargarBase'))
printHtmlPart(17)
if(true && (controllerName == "gestion" && actionName == "retirarBase")) {
printHtmlPart(18)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/gestion/retirarBase'))
printHtmlPart(19)
if(true && (controllerName == "asignarBaseNew" && actionName == "index")) {
printHtmlPart(18)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/asignarBaseNew/index'))
printHtmlPart(20)
if(true && (controllerName == "habilitarBase")) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/habilitarBase/index'))
printHtmlPart(21)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraPrincipales')||Util.checkAccess(session.user.usuario, '/reportes/gerencial'))) {
printHtmlPart(22)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/baseGestionada'))) {
printHtmlPart(23)
if(true && (actionName == "baseGestionada")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/baseGestionada'))
printHtmlPart(25)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraTrama'))) {
printHtmlPart(23)
if(true && (actionName == "bitacoraTrama")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/bitacoraTrama'))
printHtmlPart(26)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraPrincipales'))) {
printHtmlPart(23)
if(true && (actionName == "bitacoraPrincipales")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/bitacoraPrincipales'))
printHtmlPart(27)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraGestion'))) {
printHtmlPart(28)
if(true && (actionName == "bitacoraGestion")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/bitacoraGestion'))
printHtmlPart(29)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/indicadoresGestion'))) {
printHtmlPart(23)
if(true && (actionName == "indicadoresGestion")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/indicadoresGestion'))
printHtmlPart(30)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/tiemposBreak'))) {
printHtmlPart(31)
if(true && (actionName == "tiemposBreak")) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/reportes/tiemposBreak'))
printHtmlPart(32)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/loginAgentes'))) {
printHtmlPart(23)
if(true && (actionName == "loginAgentes")) {
printHtmlPart(18)
}
printHtmlPart(24)
expressionOut.print(createLink(uri: '/reportes/loginAgentes'))
printHtmlPart(33)
}
printHtmlPart(34)
}
printHtmlPart(35)
}
printHtmlPart(36)
if(true && (controllerName == "gestion" && (actionName == "index" || actionName == "gestionCliente"))) {
printHtmlPart(1)
}
printHtmlPart(2)
expressionOut.print(createLink(uri: '/gestion/index'))
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1596212785312L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
