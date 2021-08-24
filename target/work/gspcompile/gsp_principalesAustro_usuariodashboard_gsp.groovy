import callcenter.Clientes
import com.pw.security.*
import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_usuariodashboard_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/usuario/dashboard.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(4)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(4)
invokeTag('javascript','asset',10,['src':("usogeneral/moment.min.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',11,['src':("usuario/dashboard.js")],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(9)
expressionOut.print(Util.getContactabilidadMensual())
printHtmlPart(10)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(9)
expressionOut.print(Util.getContactabilidadDiaria())
printHtmlPart(11)
expressionOut.print(createLink(uri:'/usuario/'))
printHtmlPart(12)
expressionOut.print(Util.getOperadoresLogueados())
printHtmlPart(13)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(14)
expressionOut.print(Util.getContactados())
printHtmlPart(15)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(16)
expressionOut.print(Util.getCantidadCu1())
printHtmlPart(17)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(18)
expressionOut.print(Util.getAExitosasMes())
printHtmlPart(19)
for( _it146054828 in (inicioSesion) ) {
changeItVariable(_it146054828)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:'Activa')
printHtmlPart(22)
}
printHtmlPart(23)
for( _it169685675 in (ventasPorUsuario) ) {
changeItVariable(_it169685675)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(22)
}
printHtmlPart(24)
for( _it48856509 in (tablaResult) ) {
changeItVariable(_it48856509)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(21)
expressionOut.print(it[3]?:0)
printHtmlPart(22)
}
printHtmlPart(25)
expressionOut.print(totalGestionados)
printHtmlPart(26)
expressionOut.print(totalContactados)
printHtmlPart(26)
expressionOut.print(totalNoContactados)
printHtmlPart(27)
for( _it792593609 in (tablaResult1) ) {
changeItVariable(_it792593609)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(22)
}
printHtmlPart(28)
expressionOut.print(fechaHoraActualizacion)
printHtmlPart(29)
expressionOut.print(totalEfectivosGrupo)
printHtmlPart(30)
expressionOut.print(totalMetaGrupo)
printHtmlPart(30)
expressionOut.print(totalFaltantesGrupo)
printHtmlPart(30)
expressionOut.print(totalPorcentajeMetaGrupo)
printHtmlPart(30)
expressionOut.print(totalPromedioHoraGrupo)
printHtmlPart(31)
for( _it279724122 in (tableTiemposAgentes) ) {
changeItVariable(_it279724122)
printHtmlPart(32)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1]?:0)
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(21)
expressionOut.print(it[3]?:0)
printHtmlPart(21)
expressionOut.print(it[4]?:0)
printHtmlPart(33)
expressionOut.print(it[5]?:0)
printHtmlPart(21)
expressionOut.print(it[6]?:0)
printHtmlPart(21)
expressionOut.print(it[7]?:0)
printHtmlPart(21)
expressionOut.print(it[8]?:0)
printHtmlPart(21)
expressionOut.print(it[9]?:0)
printHtmlPart(33)
expressionOut.print(it[10]?:0)
printHtmlPart(21)
expressionOut.print(it[11]?:0)
printHtmlPart(21)
expressionOut.print(it[12]?:0)
printHtmlPart(34)
}
printHtmlPart(35)
invokeTag('javascript','asset',424,['src':("highcharts/data.js")],-1)
printHtmlPart(36)
invokeTag('javascript','asset',424,['src':("highcharts/drilldown.js")],-1)
printHtmlPart(36)
invokeTag('javascript','asset',425,['src':("highcharts/exporting.js")],-1)
printHtmlPart(36)
invokeTag('javascript','asset',425,['src':("highcharts/highcharts.js")],-1)
printHtmlPart(37)
})
invokeTag('captureBody','sitemesh',425,[:],1)
printHtmlPart(38)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1599443267937L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
