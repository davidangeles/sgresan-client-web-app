<%-- 
    Document   : showFile
    Created on : 19/11/2014, 12:31:42 PM
    Author     : Joel
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="javax.faces.context.ExternalContext"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="vista.Conexion"%>
<%@page import="java.io.File"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
          <%
             Conexion conn = new  Conexion();
             String numFile =  request.getParameter("file").toString();
        try
        {
            conn.conectar();
            
            Map parametros = new HashMap();
            parametros.put("id", numFile);

    
                File reportfile = new File(request.getRealPath("/reporte/reporte.jasper"));
                byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parametros, conn.getConn());   
                
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                response.setHeader("Content-Disposition","inline;filename=\"reporte.pdf\"");
                OutputStream output = response.getOutputStream();
                output.write(bytes, 0, bytes.length);
            
  
//             ec.responseReset(); 
//        ec.setResponseContentType(ec.getMimeType(pdfPath)); 
//        //ec.setResponseContentLength(contentLength); 
//        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + pdfName + "\""); 
//       InputStream input = new FileInputStream(pdfPath);
//        OutputStream output = ec.getResponseOutputStream();
//        IOUtils.copy(input, output);
 
        System.out.println("Sending to browser...");

//        fc.responseComplete();  
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
        }

              %> 
    </body>
</html>
