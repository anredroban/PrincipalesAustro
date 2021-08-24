import com.pw.security.Rol
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_rol_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/rol/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: rolInstance, field: 'nombre', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("rol.nombre.label"),'default':("Nombre")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("nombre"),'required':(""),'value':(rolInstance?.nombre)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: rolInstance, field: 'permisos', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("rol.permisos.label"),'default':("Permisos")],-1)
printHtmlPart(5)
invokeTag('select','g',19,['name':("permisos"),'from':(com.pw.security.Permiso.list()),'multiple':("multiple"),'optionKey':("id"),'size':("30"),'value':(rolInstance?.permisos*.id),'class':("many-to-many"),'optionValue':({it.descripcion})],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1493844622000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
