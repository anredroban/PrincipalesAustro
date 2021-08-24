package callcenter

class HabilitarBaseController {

    //Evita que ingrese mediante url
    static beforeInterceptor = {
        String accion = actionUri;
        if (!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")) {
            if (!session.user) {
                redirect(uri: "/usuario/login");
                return false;
            } else {
                boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
                if (!tienePermiso) {
                    render "No tienes permiso para ingresar a este sitio-> " + accion;
                }
            }
        }
    }

    def index() { }
}
