import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',8,['default':("Bienvenida Austro")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(2)
expressionOut.print(assetPath(src: 'favicon.png'))
printHtmlPart(3)
invokeTag('javascript','asset',13,['src':("main/jquery.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',14,['src':("main/custom.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',15,['src':("main/datatable.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',16,['src':("main/customdatatables.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',17,['src':("main/timer.js")],-1)
printHtmlPart(4)
invokeTag('stylesheet','asset',20,['src':("main/main.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',21,['src':("main/bootstrap.min.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',22,['src':("main/sb-admin.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',23,['src':("main/sidebar.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',24,['src':("telephony/operatorbar.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',25,['src':("main/font-awesome.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',26,['src':("main/font-awesome-animation.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',27,['src':("main/custom.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',28,['src':("main/portlet.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',29,['src':("main/datatable.css")],-1)
printHtmlPart(5)
expressionOut.print(request.getServerName())
printHtmlPart(6)
expressionOut.print(request.getServerPort())
printHtmlPart(7)
expressionOut.print(request.getContextPath())
printHtmlPart(8)
invokeTag('layoutHead','g',32,[:],-1)
printHtmlPart(9)
})
invokeTag('captureHead','sitemesh',33,[:],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
expressionOut.print(assetPath(src: 'logo-big.png'))
printHtmlPart(12)
if(true && (session.user)) {
printHtmlPart(13)
expressionOut.print(assetPath(src: 'avatarsmall.png'))
printHtmlPart(14)
expressionOut.print(session.user.nombre)
printHtmlPart(15)
createClosureForHtmlPart(16, 3)
invokeTag('link','g',53,['action':("logout"),'controller':("usuario")],3)
printHtmlPart(17)
if(true && (Util.isOperator(session.user.usuario))) {
printHtmlPart(18)
}
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('render','g',75,['template':("/layouts/sidebar")],-1)
printHtmlPart(21)
invokeTag('layoutBody','g',78,[:],-1)
printHtmlPart(22)
invokeTag('render','g',84,['template':("/layouts/operatorbar")],-1)
printHtmlPart(23)
invokeTag('javascript','asset',104,['src':("main/bootstrap.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',105,['src':("telephony/operatorbar.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',106,['src':("telephony/asteriskAmi.js")],-1)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',107,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1567027539020L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
