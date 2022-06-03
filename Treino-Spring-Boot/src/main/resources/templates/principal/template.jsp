<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/includes/head.jsp" />

  <body>
 	
  <!-- Pre-loader start -->	
  <jsp:include page="/includes/theme-loader.jsp" />
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
		
		<jsp:include page="/includes/${imports.get(0)}" />

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
				
				<jsp:include page="/includes/${imports.get(1)}" />
				
                  <div class="pcoded-content">
                      <!-- Page-header start -->
						<jsp:include page="/includes/pageheader.jsp" />
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                           <h1>Conteúdo das páginas do sistema.</h1>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="/includes/jsfile.jsp" />
    
</body>

</html>
