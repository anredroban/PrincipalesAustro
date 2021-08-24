import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_reportesindicadoresGestion_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reportes/indicadoresGestion.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',2,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("content-type"),'content':("application/vnd.ms-excel; charset=UTF-8")],-1)
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',7,['src':("usogeneral/moment.min.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',8,['src':("usogeneral/daterangepicker.js")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',9,['src':("usogeneral/daterangepicker.css")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',10,['src':("usogeneral/customdaterangepicker.js")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',11,['src':("usogeneral/customdaterangepicker.css")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('submitButton','g',21,['class':("btn btn-success"),'name':("btnEnviar"),'value':("Generar Reporte")],-1)
printHtmlPart(4)
})
invokeTag('form','g',23,['action':("indicadoresGestion"),'class':("col-lg-12")],1)
printHtmlPart(5)
if(true && (visibilizar)) {
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',27,['style':("margin-bottom: 10px"),'class':("btn btn-default exportarIndicadores"),'url':("#"),'onclick':("return false;")],2)
printHtmlPart(8)
for( _it1048982275 in (tablaResult) ) {
changeItVariable(_it1048982275)
printHtmlPart(9)
expressionOut.print(it[10]?:0)
printHtmlPart(10)
expressionOut.print(it[0])
printHtmlPart(10)
expressionOut.print(it[1])
printHtmlPart(10)
expressionOut.print(it[2]?:0)
printHtmlPart(10)
expressionOut.print(it[7]?:0)
printHtmlPart(10)
expressionOut.print(it[4]?:0)
printHtmlPart(10)
expressionOut.print(it[9]?:0)
printHtmlPart(10)
expressionOut.print(it[8]?:0)
printHtmlPart(11)
}
printHtmlPart(12)
expressionOut.print(totalGestionados)
printHtmlPart(13)
expressionOut.print(totalContactados)
printHtmlPart(13)
expressionOut.print(totalNoDeseaCU5)
printHtmlPart(13)
expressionOut.print(totalExitosos)
printHtmlPart(14)
expressionOut.print(totalPorcentajeEfectividad)
printHtmlPart(13)
expressionOut.print(totalPorcentajeContactabilidad)
printHtmlPart(15)
}
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1567026277732L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
