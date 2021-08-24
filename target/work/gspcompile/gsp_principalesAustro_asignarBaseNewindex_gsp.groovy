import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_asignarBaseNewindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/asignarBaseNew/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',5,['src':("asignarBaseNew/index.js")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
createClosureForHtmlPart(6, 3)
invokeTag('select','g',20,['class':("form-control"),'name':("consultants"),'size':("35"),'id':("consultants"),'required':("true"),'multiple':("multiple"),'from':(utilitarios.Util.getOperDisponiblesAsignacion()),'optionKey':("id"),'optionValue':({ it.nombre })],3)
printHtmlPart(7)
invokeTag('textField','g',34,['class':("form-control"),'name':("desde")],-1)
printHtmlPart(8)
invokeTag('textField','g',40,['class':("form-control"),'name':("hasta")],-1)
printHtmlPart(9)
invokeTag('textField','g',56,['class':("form-control"),'name':("intentosDesde")],-1)
printHtmlPart(8)
invokeTag('textField','g',62,['class':("form-control"),'name':("intentosHasta")],-1)
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('select','g',79,['class':("form-control"),'name':("dbName"),'id':("dbName"),'required':("true"),'from':(utilitarios.Util.getNombresBase()),'noSelection':(['': '-Seleccione-'])],3)
printHtmlPart(12)
createClosureForHtmlPart(11, 3)
invokeTag('select','g',88,['class':("form-control"),'name':("tipoRegx"),'id':("tipoRegx"),'required':("true"),'from':(['SIN GESTION', 'REGESTIONABLES', 'RELLAMADAS']),'noSelection':(['': '-Seleccione-'])],3)
printHtmlPart(13)
createClosureForHtmlPart(11, 3)
invokeTag('select','g',97,['class':("form-control"),'name':("subestadosRegestionables"),'id':("subestadosRegestionables"),'from':(utilitarios.Util.getSubestadosRegestionables()),'noSelection':(['': '-Seleccione-']),'optionKey':("name"),'optionValue':({it.name})],3)
printHtmlPart(14)
invokeTag('textField','g',107,['required':("true"),'class':("form-control"),'name':("allocationNumber")],-1)
printHtmlPart(15)
if(true && (baseAsignada)) {
printHtmlPart(16)
expressionOut.print(registrosAsignados)
printHtmlPart(17)
}
printHtmlPart(18)
})
invokeTag('form','g',123,['controller':("asignarBaseNew"),'action':("index")],2)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',124,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1565203600363L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
