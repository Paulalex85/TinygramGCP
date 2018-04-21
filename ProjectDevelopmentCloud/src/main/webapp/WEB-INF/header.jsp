<%@ page language="java" contentType="text/html"%>
<%@ page import="com.google.appengine.api.users.*" %>
<% UserService userService = UserServiceFactory.getUserService(); %>
<!DOCTYPE html>
<html>

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tinygram</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="vendor/simple-line-icons/css/simple-line-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet">

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="device-mockups/device-mockups.min.css">

    <!-- Custom styles for this template -->
    <link href="css/new-age.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" ng-click="changeView('')">Tinygram</a>
        <div id="navBarPseudo" class="nav-link"></div>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <%if (userService.getCurrentUser() == null) { %>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="<%= userService.createLoginURL("/") %>" >Connexion</a>
            </li>
            <% }
            else {%>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="/timeline">Timeline</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" >Bonjour <%= userService.getCurrentUser().getNickname() %></a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="<%= userService.createLogoutURL("/") %>">Deconnexion</a>
            </li>
            <% } %>
          </ul>
          <div id="content"></div>
        </div>
      </div>
    </nav>