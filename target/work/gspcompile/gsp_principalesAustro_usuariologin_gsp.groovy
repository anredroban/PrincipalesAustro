import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_principalesAustro_usuariologin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/usuario/login.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=${encoding}")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
expressionOut.print(assetPath(src: 'favicon.ico'))
printHtmlPart(4)
expressionOut.print(request.getServerName())
printHtmlPart(5)
expressionOut.print(request.getServerPort())
printHtmlPart(6)
expressionOut.print(request.getContextPath())
printHtmlPart(7)
invokeTag('stylesheet','asset',12,['src':("usuario/login/reset.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',13,['src':("usuario/login/style.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',14,['src':("usuario/login/supersized.css")],-1)
printHtmlPart(8)
})
invokeTag('captureHead','sitemesh',15,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
if(true && (flash.errorMessage)) {
printHtmlPart(11)
expressionOut.print(flash.errorMessage)
printHtmlPart(12)
}
printHtmlPart(13)
})
invokeTag('form','g',30,['action':("login")],2)
printHtmlPart(14)
expressionOut.print(assetPath(src: 'logo-big.png'))
printHtmlPart(15)
invokeTag('javascript','asset',40,['src':("main/jquery.js")],-1)
printHtmlPart(8)
invokeTag('javascript','asset',41,['src':("usuario/login/scripts.js")],-1)
printHtmlPart(8)
invokeTag('javascript','asset',42,['src':("usuario/login/supersized-init.js")],-1)
printHtmlPart(8)
invokeTag('javascript','asset',43,['src':("usuario/login/supersized.3.2.7.min.js")],-1)
printHtmlPart(8)
})
invokeTag('captureBody','sitemesh',44,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1629086851734L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
