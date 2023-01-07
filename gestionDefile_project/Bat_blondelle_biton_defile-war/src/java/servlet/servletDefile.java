/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entites.AdminUser;
import entites.Bijoux;
import entites.Chaussures;
import entites.Couturier;
import entites.Defile;
import entites.Lieu;
import entites.Mannequin;
import entites.Organisateur;
import entites.Vestimentaire;
import entites.Vetement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.GrandPublicLocal;
import sessions.OrganisationLocal;
import sessions.SessionAdminLocal;
import sessions.SessionCouturierLocal;
import sessions.SessionMannequinLocal;
import typeEnum.Type;

/**
 *
 * @author matti
 */
@WebServlet(name = "servletDefile", urlPatterns = {"/servletDefile"})
public class servletDefile extends HttpServlet {

    GrandPublicLocal grandPublic = lookupGrandPublicLocal();

    @EJB
    private SessionAdminLocal sessionAdmin;
    
    @EJB
    private SessionMannequinLocal sessionMannequin;
    
    @EJB
    private SessionCouturierLocal sessionCouturier;
    
    @EJB
    private OrganisationLocal sessionOrganisateur;
    
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String jspClient = null;
        String act = request.getParameter("action");
        
        if(null == act) {
            jspClient="/home.jsp";
            request.setAttribute("message", "Rien à déclarer. :-)");
        } else switch (act) {
            case "vide":
                jspClient="/home.jsp";
                request.setAttribute("message", "Rien à déclarer. :-)");
                break;
            case "login":
                String role = request.getParameter("radioRole");
                String login = request.getParameter("login");
                String mdp = request.getParameter("pw");
                switch (role) {
                    case "admin":
                        AdminUser ad = sessionAdmin.loginAdmin(login, mdp);
                        if(ad != null) {
                            HttpSession sessionRole = request.getSession(true);
                            jspClient = "/menu.jsp";
                            sessionRole.setAttribute("accountID", ad.getId());
                            sessionRole.setAttribute("surnameAccount", ad.getNom());
                            sessionRole.setAttribute("nameAccount", ad.getPrenom());
                            sessionRole.setAttribute("roleAccount", role);
                        } else {
                            jspClient = "/login.jsp";
                            request.setAttribute("message", "Informations incorrects.");
                        }   break;
                    case "mannequin":
                        Mannequin ma = sessionMannequin.loginMannequin(login, mdp);
                        if(ma != null) {
                            HttpSession sessionRole = request.getSession(true);
                            jspClient = "/menu.jsp";
                            sessionRole.setAttribute("accountID", ma.getId());
                            sessionRole.setAttribute("surnameAccount", ma.getNom());
                            sessionRole.setAttribute("nameAccount", ma.getPrenom());
                            sessionRole.setAttribute("roleAccount", role);
                        } else {
                            jspClient = "/login.jsp";
                            request.setAttribute("message", "Informations incorrects.");
                        } break;
                    case "couturier":
                        Couturier co = sessionCouturier.authentificationCout(login, mdp);
                        if(co != null) {
                            HttpSession sessionRole = request.getSession(true);
                            jspClient = "/menu.jsp";
                            sessionRole.setAttribute("accountID", co.getId());
                            sessionRole.setAttribute("surnameAccount", co.getNom());
                            sessionRole.setAttribute("nameAccount", co.getPrenom());
                            sessionRole.setAttribute("roleAccount", role);
                        } else {
                            jspClient = "/login.jsp";
                            request.setAttribute("message", "Informations incorrects.");
                        }   break;
                    case "organisateur":
                        Organisateur org = sessionOrganisateur.authentificationOrga(login, mdp);
                        if(org != null) {
                            HttpSession sessionRole = request.getSession(true);
                            jspClient = "/menu.jsp";
                            sessionRole.setAttribute("accountID", org.getId());
                            sessionRole.setAttribute("surnameAccount", org.getNom());
                            sessionRole.setAttribute("nameAccount", org.getPrenom());
                            sessionRole.setAttribute("roleAccount", role);
                        } else {
                            jspClient = "/login.jsp";
                            request.setAttribute("message", "Informations incorrects.");
                        }   break;
                    default:
                        break;
                }   break;
            case "createCouturier":
                jspClient = "createCouturier.jsp";
                doActionCreerCouturier(request, response);
                break;
            case "createOrganisateur":
                jspClient = "/menu.jsp";
                doActionCreerOrganisateur(request, response);
                break;
            case "createMannequin":
                jspClient = "/jsp/create/createMannequin.jsp";
                doActionCreerMannequin(request, response);
                break;
            case "createLieu":
                jspClient = "/menu.jsp";
                doActionCreerLieu(request, response);
                break;
            case "createAccessoire":
                jspClient = "/menu.jsp";
                doActionCreerAccessoire(request, response);
                break;
            case "createVetement":
                {
                    jspClient = "/jsp/create/createVetement.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    Collection <Bijoux> listB = sessionCouturier.recherchebijouxcout(idCouturier);
                    request.setAttribute("listeBijoux", listB);
                    Collection <Chaussures> listCh = sessionCouturier.rechercheChaussureCout(idCouturier);
                    request.setAttribute("listeChaussures", listCh);
                    Collection <Vestimentaire> listV = sessionCouturier.rechercheVestCout(idCouturier);
                    request.setAttribute("listeVestimentaires", listV);
                    Collection <Mannequin> listM = sessionCouturier.RechercherMannequinCouturier(idCouturier);
                    request.setAttribute("listeMannequins", listM);
                    Collection <Defile> listD = sessionCouturier.rechercheDefilesCouturier(idCouturier);
                    request.setAttribute("listeDefiles", listD);
                    break;
                }
            case "submitCreateVetement":
                jspClient = "/menu.jsp";
                doActionCreerVetement(request, response);
                break;
            case "createDefile":
                {
                    jspClient="/jsp/create/createDefile.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    Collection <Lieu> listL = sessionOrganisateur.RechercherLieuOrganisateur(idOrganisateur);
                    Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeLieux",listL);
                    request.setAttribute("listeInvit",listC);
                    break;
                }
            case "submitDefile":
                jspClient = "/menu.jsp";
                doActionCreerDefile(request, response);
                break;
            case "editMannequin":
                {
                    jspClient = "/jsp/edit/editMannequin.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    Collection <Mannequin> listMq = sessionCouturier.RechercherMannequinCouturier(idCouturier);
                    request.setAttribute("listeEditMannequins", listMq);
                    break;
                }
            case "submitEditMannequin":
                jspClient = "/menu.jsp";
                doActionEditMannequin(request, response);
                break;
            case "editAccessoire":
                {
                    jspClient = "/jsp/edit/editAccessoire.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    Collection <Bijoux> listB = sessionCouturier.recherchebijouxcout(idCouturier);
                    request.setAttribute("listeBijoux", listB);
                    Collection <Chaussures> listCh = sessionCouturier.rechercheChaussureCout(idCouturier);
                    request.setAttribute("listeChaussures", listCh);
                    Collection <Vestimentaire> listV = sessionCouturier.rechercheVestCout(idCouturier);
                    request.setAttribute("listeVestimentaires", listV);
                    break;
                }
            case "submitEditAccessoire":
                jspClient = "/menu.jsp";
                doActionEditAccessoire(request, response);
                break;
            case "editLieu":
                {
                    jspClient = "/jsp/edit/editLieu.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    Collection <Lieu> listLi = sessionOrganisateur.RechercherLieuOrganisateur(idOrganisateur);
                    request.setAttribute("listeLieux", listLi);
                    break;
                }
            case "submitEditLieu":
                jspClient = "/menu.jsp";
                doActionEditLieu(request, response);
                break;
            case "editCouturier":
                {
                    jspClient = "/jsp/edit/editCouturier.jsp";
                    Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeCouturiers", listC);
                    break;
                }
            case "submitEditCouturier":
                jspClient = "/menu.jsp";
                doActionEditCouturier(request, response);
                break;
            case "editOrganisateur":
                {
                    jspClient = "/jsp/edit/editOrganisateur.jsp";
                    Collection <Organisateur> listO = sessionOrganisateur.rechercheOrganisateurs();
                    request.setAttribute("listeOrganisateurs", listO);
                    break;
                }
            case "submitEditOrganisateur":
                jspClient = "/menu.jsp";
                doActionEditOrganisateur(request, response);
                break;
            case "editDefile":
                {
                    jspClient = "/jsp/edit/editDefile.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    Collection <Defile> listD = sessionOrganisateur.RechercheDefileOrga(idOrganisateur);
                    request.setAttribute("listeDefiles", listD);
                    Collection <Lieu> listL = sessionOrganisateur.RechercherLieuOrganisateur(idOrganisateur);
                    request.setAttribute("listeLieux",listL);
                    Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvit",listC);
                    break;
                }
            case "submitEditDefile":
                jspClient = "/menu.jsp";
                doActionEditDefile(request, response);
                break;
            case "editVetement":
                {
                    jspClient = "/jsp/edit/editVetement.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    Collection <Vetement> listVet = sessionCouturier.RechercherVetCouturier(idCouturier);
                    request.setAttribute("listeVetements", listVet);
                    Collection <Bijoux> listB = sessionCouturier.recherchebijouxcout(idCouturier);
                    request.setAttribute("listeBijoux", listB);
                    Collection <Chaussures> listCh = sessionCouturier.rechercheChaussureCout(idCouturier);
                    request.setAttribute("listeChaussures", listCh);
                    Collection <Vestimentaire> listV = sessionCouturier.rechercheVestCout(idCouturier);
                    request.setAttribute("listeVestimentaires", listV);
                    Collection <Mannequin> listM = sessionCouturier.RechercherMannequinCouturier(idCouturier);
                    request.setAttribute("listeMannequins", listM);
                    Collection <Defile> listD = sessionCouturier.rechercheDefilesCouturier(idCouturier);
                    request.setAttribute("listeDefiles", listD);
                    break;
                }
            case "submitEditVetement":
                jspClient = "/menu.jsp";
                doActionEditVetement(request, response);
                break;
            case "showVetementsAccessoires":
                {
                    jspClient = "/jsp/person/showVetementsAccessoires.jsp";
                    HttpSession sessionRole = request.getSession(true);
                    long idMannequin = (Long) sessionRole.getAttribute("accountID");
                    Collection <Vetement> listV = sessionMannequin.rechercheVetementsMannequin(idMannequin);
                    request.setAttribute("listeVetements", listV);
                    break;
                }
            case "infosBijou":
                {
                    String id = request.getParameter("id");
                    long idBijou = Long.parseLong(id);
                    Bijoux b = sessionMannequin.rechercheBijou(idBijou);
                    request.setAttribute("bijou", b);
                    jspClient = "/jsp/object/leBijou.jsp?id=" + idBijou;
                    break;
                }
            case "infosCouturier":
                {
                    String id = request.getParameter("id");
                    long idCouturier = Long.parseLong(id);
                    Couturier c = sessionOrganisateur.rechercheCouturiers(idCouturier);
                    request.setAttribute("couturier", c);
                    jspClient = "/jsp/object/leCouturier.jsp?id=" + idCouturier;
                    break;
                }
            case "infosChaussures":
                {
                    String id = request.getParameter("id");
                    long idChaussures = Long.parseLong(id);
                    Chaussures c = sessionCouturier.rechercheChaussuresID(idChaussures);
                    request.setAttribute("chaussures", c);
                    jspClient = "/jsp/object/lesChaussures.jsp?id=" + idChaussures;
                    break;
                }
            case "infosVestimentaire":
                {
                    String id = request.getParameter("id");
                    long idVestimentaire = Long.parseLong(id);
                    Vestimentaire v = sessionCouturier.rechercheVestimentaireID(idVestimentaire);
                    request.setAttribute("vestimentaire", v);
                    jspClient = "/jsp/object/leVestimentaire.jsp?id=" + idVestimentaire;
                    break;
                }
            case "infosDefile":
                {
                    String id = request.getParameter("id");
                    long idDefile = Long.parseLong(id);
                    Defile d = sessionOrganisateur.rechercheDefileID(idDefile);
                    request.setAttribute("defile", d);
                    jspClient = "/jsp/object/leDefile.jsp?id=" + idDefile;
                    break;
                }
            case "infosLieu":
                {
                    String id = request.getParameter("id");
                    long idLieu = Long.parseLong(id);
                    Lieu l = sessionOrganisateur.rechercheLieuID(idLieu);
                    request.setAttribute("lieu", l);
                    jspClient = "/jsp/object/leLieu.jsp?id=" + idLieu;
                    break;
                }
            case "infosOrganisateur":
                {
                    String id = request.getParameter("id");
                    long idOrganisateur = Long.parseLong(id);
                    Organisateur o = sessionAdmin.rechercheOrganisateurID(idOrganisateur);
                    request.setAttribute("organisateur", o);
                    jspClient = "/jsp/object/leOrganisateur.jsp?id=" + idOrganisateur;
                    break;
                }
            case "vetementsCouturier":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/vetementsCouturier.jsp?id=" + idCouturier;
                    Collection <Vetement> listV = sessionCouturier.RechercherVetCouturier(idCouturier);
                    for(Vetement vetement : listV) {
                        int cout = sessionCouturier.afficherCoutVetement(vetement);
                        System.out.println(cout);
                    }       request.setAttribute("listeVetements", listV);
                    break;
                }
            case "defilesOrganisateur":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/defilesOrganisateur.jsp?id=" + idOrganisateur;
                    Collection <Defile> listD = sessionOrganisateur.RechercheDefileOrga(idOrganisateur);
                    request.setAttribute("listeDefiles", listD);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listD) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvit",listC);
                    break;
                }
            case "lieuxOrganisateur":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/lieuxOrganisateur.jsp?id=" + idOrganisateur;
                    Collection <Lieu> listL = sessionOrganisateur.RechercherLieuOrganisateur(idOrganisateur);
                    request.setAttribute("listeLieux", listL);
                    break;
                }
            case "couturiersOrganisateur":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idOrganisateur = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/couturiersOrganisateur.jsp?id=" + idOrganisateur;
                    Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeCouturiers", listC);
                    break;
                }
            case "mannequinsCouturier":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/mannequinsCouturier.jsp?id=" + idCouturier;
                    Collection <Mannequin> listM = sessionCouturier.RechercherMannequinCouturier(idCouturier);
                    request.setAttribute("listeMannequins", listM);
                    break;
                }
            case "infosMannequin":
                {
                    String id = request.getParameter("id");
                    long idMannequin = Long.parseLong(id);
                    Mannequin m = sessionCouturier.rechercheMannequinID(idMannequin);
                    request.setAttribute("mannequin", m);
                    jspClient = "/jsp/object/leMannequin.jsp?id=" + idMannequin;
                    break;
                }
            case "accessoiresCouturier":
                {
                    HttpSession sessionRole = request.getSession(true);
                    long idCouturier = (Long) sessionRole.getAttribute("accountID");
                    jspClient = "/jsp/person/accessoiresCouturier.jsp?id=" + idCouturier;
                    Collection <Bijoux> listB = sessionCouturier.recherchebijouxcout(idCouturier);
                    request.setAttribute("listeBijoux", listB);
                    Collection <Chaussures> listCh = sessionCouturier.rechercheChaussureCout(idCouturier);
                    request.setAttribute("listeChaussures", listCh);
                    Collection <Vestimentaire> listVest = sessionCouturier.rechercheVestCout(idCouturier);
                    request.setAttribute("listeVestimentaires", listVest);
                    break;
                }
            case "lesOrganisateurs":
                {
                    jspClient = "/jsp/person/lesOrganisateurs.jsp";
                    Collection <Organisateur> listO = sessionAdmin.rechercheOrganisateurs();
                    request.setAttribute("listeOrganisateurs", listO);
                    break;
                }
            case "rechercheDefiles":
                jspClient = "/rechercheDefiles.jsp";
                Collection <Lieu> listL = sessionOrganisateur.rechercheLieux();
                request.setAttribute("listeLieux", listL);
                Collection <Couturier> listC = sessionOrganisateur.rechercheInvites();
                request.setAttribute("listeCouturiers", listC);
                Collection <Mannequin> listM = sessionCouturier.rechercheMannequins();
                request.setAttribute("listeMannequins", listM);
                break;
            case "submitSearchLieu":
                {
                    String idLieuString = request.getParameter("idLieu");
                    long idLieu = Long.parseLong(idLieuString);
                    jspClient = "/jsp/results/searchByLieu.jsp?id=" + idLieuString;
                    List<Defile> listResult = grandPublic.searchByLieu(idLieu);
                    request.setAttribute("listeDefiles", listResult);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listResult) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvites", listInvit);                
                    break;
                }
            case "submitSearchCouturier":
                {
                    String idCouturierString = request.getParameter("idCouturier");
                    long idCouturier = Long.parseLong(idCouturierString);
                    jspClient = "/jsp/results/searchByCouturier.jsp?id=" + idCouturierString;
                    List<Defile> listResult = grandPublic.searchByCouturier(idCouturier);
                    request.setAttribute("listeDefiles", listResult);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listResult) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvites", listInvit);                
                    break;
                }
            case "submitSearchMannequin":
                {
                    String idMannequinString = request.getParameter("idMannequin");
                    long idMannequin = Long.parseLong(idMannequinString);
                    jspClient = "/jsp/results/searchByMannequin.jsp?id=" + idMannequinString;
                    List<Defile> listResult = grandPublic.searchByMannequin(idMannequin);
                    request.setAttribute("listeDefiles", listResult);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listResult) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvites", listInvit);                
                    break;
                }
            case "submitSearchDate":
                {
                    try {
                        String dateString = request.getParameter("input-date");
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateDefile = df.parse(dateString);
                        jspClient = "/jsp/results/searchByDate.jsp?date=" + dateString;
                        List<Defile> listResult = grandPublic.searchByDate(dateDefile);
                        request.setAttribute("listeDefiles", listResult);
                        Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                        for (Defile defile : listResult) {
                            double cout = sessionOrganisateur.returnCalculCout(defile);
                            defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                        }
                        request.setAttribute("defileCoutMap", defileCoutMap);
                        Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                        request.setAttribute("listeInvites", listInvit);                
                        break; 
                    } catch (ParseException ex) {
                        request.setAttribute("message", "Il y a eu une erreur.");
                    }                 
                }
            case "submitSearchIntervalle":
                {
                    String date1String = request.getParameter("input-date-1");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = df.parse(date1String);
                    String date2String = request.getParameter("input-date-2");
                    Date date2 = df.parse(date2String);
                    jspClient = "/jsp/results/searchByDate.jsp";
                    List<Defile> listResult = grandPublic.searchByIntervalle(date1, date2);
                    request.setAttribute("listeDefiles", listResult);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listResult) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvites", listInvit);                
                    break; 
                }
            case "submitSearchCout":
                {
                    String coutString = request.getParameter("input-cout");
                    double coutDouble = Double.parseDouble(coutString);
                    request.setAttribute("coutSearch", coutDouble);
                    String deltaString = request.getParameter("input-delta");
                    double delta = Double.parseDouble(deltaString);
                    request.setAttribute("delta", delta);
                    jspClient = "/jsp/results/searchByCout.jsp?cout=" + coutString;
                    List<Defile> listResult = grandPublic.searchAllDefiles();
                    System.out.println(listResult);
                    request.setAttribute("listeDefiles", listResult);
                    Map<Defile, Double> defileCoutMap = new HashMap<>(); /* Pour récuperer un coût et le relier à son défilé */
                    for (Defile defile : listResult) {
                        double cout = sessionOrganisateur.returnCalculCout(defile);
                        defileCoutMap.put(defile, cout); /* On ajoute le défilé et son coût à la map */
                    }
                    request.setAttribute("defileCoutMap", defileCoutMap);
                    Collection <Couturier> listInvit = sessionOrganisateur.rechercheInvites();
                    request.setAttribute("listeInvites", listInvit);                
                    break;
                }
            default:
                break;
        }
        
        RequestDispatcher Rd;
        Rd = getServletContext().getRequestDispatcher(jspClient);
        Rd.forward(request, response);

        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletDefile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletDefile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void doActionCreerCouturier (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String prenomCouturier = request.getParameter("prenomCouturier");
            String nomCouturier = request.getParameter("nomCouturier");
            String maisonCouture = request.getParameter("maisonCouture");
            String phoneCouturier = request.getParameter("phoneCouturier");
            String adresseCouturier = request.getParameter("adresseCouturier");
            String codePostal = request.getParameter("codePostal");
            String villeCouturier = request.getParameter("villeCouturier");
            
            String loginCouturier = prenomCouturier.toLowerCase().concat(".").concat(nomCouturier.toLowerCase());
            String pwCouturier1 = request.getParameter("pwCouturier1");
            String pwCouturier2 = request.getParameter("pwCouturier2");
            
            if(pwCouturier1.equals(pwCouturier2)) {
                sessionOrganisateur.newCouturier(nomCouturier, prenomCouturier, adresseCouturier, villeCouturier, codePostal, phoneCouturier, loginCouturier, pwCouturier1, maisonCouture);
                request.setAttribute("message", "Le couturier a bien été créé — son identifiant est " + loginCouturier);
            } else {
                request.setAttribute("message", "Les mots de passe ne correspondent pas.");
            }
    }
    
    protected void doActionEditCouturier (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String editClicked = request.getParameter("submit");
            String removeClicked = request.getParameter("delete");
            
            String prenomCouturier = request.getParameter("prenomCouturier");
            String nomCouturier = request.getParameter("nomCouturier");
            String maisonCouture = request.getParameter("maisonCouturier");
            String phoneCouturier = request.getParameter("phoneCouturier");
            String adresseCouturier = request.getParameter("adresseCouturier");
            String codePostal = request.getParameter("cpCouturier");
            String villeCouturier = request.getParameter("villeCouturier");
            String idCouturierString = request.getParameter("idCouturier");
            long idCouturier = Long.parseLong(idCouturierString);
            
            String loginCouturier = prenomCouturier.toLowerCase().concat(".").concat(nomCouturier.toLowerCase());
            String pwCouturier1 = request.getParameter("pwCouturier1");
            String pwCouturier2 = request.getParameter("pwCouturier2");
            
            if(editClicked != null) {
                if(pwCouturier1.equals(pwCouturier2)) {
                    sessionOrganisateur.modifierCouturier(nomCouturier, prenomCouturier, adresseCouturier, villeCouturier, codePostal, phoneCouturier, loginCouturier, pwCouturier1, maisonCouture, idCouturier);
                    request.setAttribute("message", "Le couturier " + loginCouturier + " a bien été modifié.");
                }
            } else if(removeClicked != null) {
               sessionOrganisateur.supprimerCouturier(idCouturier);
               request.setAttribute("message", "Le couturier " + loginCouturier + " a bien été supprimé.");
            }
    }   
    
    
    protected void doActionCreerOrganisateur (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String prenomOrganisateur = request.getParameter("prenomOrganisateur");
            String nomOrganisateur = request.getParameter("nomOrganisateur");
            String societeOrg = request.getParameter("societeOrg");
            String phoneOrganisateur = request.getParameter("phoneOrganisateur");
            String adresseOrganisateur = request.getParameter("adresseOrganisateur");
            String codePostal = request.getParameter("codePostal");
            String villeOrganisateur = request.getParameter("villeOrganisateur");
            
            String loginOrganisateur = prenomOrganisateur.toLowerCase().concat(".").concat(nomOrganisateur.toLowerCase());
            String pwCouturier1 = request.getParameter("pwCouturier1");
            String pwCouturier2 = request.getParameter("pwCouturier2");
            
            if(pwCouturier1.equals(pwCouturier2)) {
                sessionAdmin.newOrganisateur(nomOrganisateur, prenomOrganisateur, adresseOrganisateur, villeOrganisateur, codePostal, phoneOrganisateur, loginOrganisateur, pwCouturier1, societeOrg);
                request.setAttribute("message", "L'organisateur a bien été créé — son identifiant est " + loginOrganisateur);
            } else {
                request.setAttribute("message", "Les mots de passe ne correspondent pas.");
            }
    }
    
    protected void doActionEditOrganisateur (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String editClicked = request.getParameter("submit");
            String removeClicked = request.getParameter("delete");
            
            String prenomOrganisateur = request.getParameter("prenomOrganisateur");
            String nomOrganisateur = request.getParameter("nomOrganisateur");
            String societeOrg = request.getParameter("societeOrganisateur");
            String phoneOrganisateur = request.getParameter("phoneOrganisateur");
            String adresseOrganisateur = request.getParameter("adresseOrganisateur");
            String codePostal = request.getParameter("codePostal");
            String villeOrganisateur = request.getParameter("villeOrganisateur");
            String idOrganisateurString = request.getParameter("idOrganisateur");
            long idOrganisateur = Long.parseLong(idOrganisateurString);
            
            String loginOrganisateur = prenomOrganisateur.toLowerCase().concat(".").concat(nomOrganisateur.toLowerCase());
            String pwOrganisateur1 = request.getParameter("pwOrganisateur1");
            String pwOrganisateur2 = request.getParameter("pwOrganisateur2");
            
            if(editClicked != null) {
                if(pwOrganisateur1.equals(pwOrganisateur2)) {
                    sessionAdmin.modifierOrganisateur(nomOrganisateur, prenomOrganisateur, societeOrg, adresseOrganisateur, villeOrganisateur, codePostal, phoneOrganisateur, loginOrganisateur, pwOrganisateur1, idOrganisateur);
                    request.setAttribute("message", "L'organisateur " + loginOrganisateur + " a bien été modifié.");
                }
            } else if(removeClicked != null) {
               sessionAdmin.supprimerOrganisateur(idOrganisateur);
               request.setAttribute("message", "L'organisateur " + loginOrganisateur + " a bien été supprimé.");
            }
    }
    
    protected void doActionCreerMannequin (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String prenomMannequin = request.getParameter("prenomMannequin");
            String nomMannequin = request.getParameter("nomMannequin");
            String poidsMannequinString = request.getParameter("poidsMannequin");
            double poidsMannequin = Double.parseDouble(poidsMannequinString);
            String tailleMannequinString = request.getParameter("tailleMannequin");
            double tailleMannequin = Double.parseDouble(tailleMannequinString);
            String prixPrestaString = request.getParameter("prixPresta");
            double prixPresta = Double.parseDouble(prixPrestaString);
            String phoneMannequin = request.getParameter("phoneMannequin");
            String adresseMannequin = request.getParameter("adresseMannequin");
            String codePostal = request.getParameter("codePostal");
            String villeMannequin = request.getParameter("villeMannequin");
            String couturierIDString = request.getParameter("accountID");
            Long couturierID = Long.parseLong(couturierIDString);
            
            String loginMannequin = prenomMannequin.toLowerCase().concat(".").concat(nomMannequin.toLowerCase());
            String pwMannequin1 = request.getParameter("pwMannequin1");
            String pwMannequin2 = request.getParameter("pwMannequin2");
            
            if(pwMannequin1.equals(pwMannequin2)) {
                sessionCouturier.CreerMannequin(tailleMannequin, prixPresta, poidsMannequin, nomMannequin, prenomMannequin, phoneMannequin, codePostal, loginMannequin, pwMannequin1, adresseMannequin, villeMannequin, couturierID);
                request.setAttribute("message", "Le mannequin a bien été créé — son identifiant est " + loginMannequin);
            } else {
                request.setAttribute("message", "Les mots de passe ne correspondent pas.");
            }
    }
    
    protected void doActionEditMannequin (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String mannequinIDString = request.getParameter("idMannequinSelected");
            long mannequinID = Long.parseLong(mannequinIDString);
            String editClicked = request.getParameter("submit");
            String removeClicked = request.getParameter("delete");
            String prenomMannequin = request.getParameter("prenomMannequin");
            String nomMannequin = request.getParameter("nomMannequin");
            String poidsMannequinString = request.getParameter("poidsMannequin");
            double poidsMannequin = Double.parseDouble(poidsMannequinString);
            String tailleMannequinString = request.getParameter("tailleMannequin");
            double tailleMannequin = Double.parseDouble(tailleMannequinString);
            String prixPrestaString = request.getParameter("prixPresta");
            double prixPresta = Double.parseDouble(prixPrestaString);
            String phoneMannequin = request.getParameter("phoneMannequin");
            String adresseMannequin = request.getParameter("adresseMannequin");
            String codePostal = request.getParameter("codePostal");
            String villeMannequin = request.getParameter("villeMannequin");
            String couturierIDString = request.getParameter("accountID");
            long couturierID = Long.parseLong(couturierIDString);
            
            String loginMannequin = prenomMannequin.toLowerCase().concat(".").concat(nomMannequin.toLowerCase());
            String pwMannequin1 = request.getParameter("pwMannequin1");
            String pwMannequin2 = request.getParameter("pwMannequin2");
            
            if(editClicked != null) {
                if(pwMannequin1.equals(pwMannequin2)) {
                sessionCouturier.ModifierMannequin(nomMannequin, prenomMannequin, adresseMannequin, villeMannequin, codePostal, phoneMannequin, loginMannequin, pwMannequin1, prixPresta, mannequinID , poidsMannequin, tailleMannequin);
                request.setAttribute("message", "Le mannequin " + loginMannequin + " a bien été modifié.");
                }
            } else if(removeClicked != null) {
                sessionCouturier.supprimerMannequin(mannequinID);
                request.setAttribute("message", "Le mannequin " + loginMannequin + " a bien été supprimé.");
            }
    }
    
    protected void doActionCreerLieu (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String nomLieu = request.getParameter("nomLieu");
            String adresseLieu = request.getParameter("adresseLieu");
            String cpLieu = request.getParameter("cpLieu");
            String villeLieu = request.getParameter("villeLieu");
            String organisateurIDString = request.getParameter("accountID");
            long organisateurID = Long.parseLong(organisateurIDString);
            
            sessionOrganisateur.CreerLieu(nomLieu, adresseLieu, villeLieu, cpLieu, organisateurID);
            request.setAttribute("message", "Le lieu " + nomLieu + " a bien été créé.");
    }
    
    protected void doActionEditLieu (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String editClicked = request.getParameter("submit");
            String removeClicked = request.getParameter("delete");
                
            String idLieuString = request.getParameter("idLieu");
            long idLieu = Long.parseLong(idLieuString);
            String nomLieu = request.getParameter("nomLieu");
            String adresseLieu = request.getParameter("adresseLieu");
            String cpLieu = request.getParameter("cpLieu");
            String villeLieu = request.getParameter("villeLieu");
            
            if(editClicked != null) {
                sessionOrganisateur.ModifierLieu(nomLieu, adresseLieu, villeLieu, cpLieu, idLieu);
                request.setAttribute("message", "Le lieu " + nomLieu + " a bien été modifié."); 
            } else if(removeClicked != null) {
                sessionOrganisateur.supprimerLieu(idLieu);
                request.setAttribute("message", "Le lieu " + nomLieu + " a bien été supprimé.");
            }                
    }
    
    protected void doActionCreerDefile (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            try {
                String dateString = request.getParameter("dateDefile");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDefile = df.parse(dateString);
                
                String nomDefile = request.getParameter("nomDefile");
                String idLieuString = request.getParameter("idLieu");
                long idLieu = Long.parseLong(idLieuString);
                String idCouturierString = request.getParameter("idCouturier");
                long idCouturier = Long.parseLong(idCouturierString);
                String idOrganisateurString = request.getParameter("accountID");
                long idOrganisateur = Long.parseLong(idOrganisateurString);
                String[] invitesArray = request.getParameterValues("idInvites");
                Couturier c = null;
                List<Couturier> couturiersInvites = new ArrayList<>();

                for (String idInvit : invitesArray) {
                    long idInv = Long.parseLong(idInvit);
                    c = sessionOrganisateur.rechercheCouturiers(idInv);
                    couturiersInvites.add(c);
                }

                sessionOrganisateur.creerDefile(nomDefile, dateDefile, idOrganisateur, idLieu, idCouturier, couturiersInvites);
                request.setAttribute("message", "Le défilé " + nomDefile + " a bien été créé.");
                System.out.println(couturiersInvites);
                
            } catch (ParseException ex) {
                request.setAttribute("message", "Il y a eu une erreur.");
            }    
    }
    
    protected void doActionEditDefile (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            try {
                String editClicked = request.getParameter("submit");
                String removeClicked = request.getParameter("delete");
                
                String idDefileString = request.getParameter("idDefile");
                long idDefile = Long.parseLong(idDefileString);
                String dateString = request.getParameter("dateDefile");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDefile = df.parse(dateString);
                String nomDefile = request.getParameter("nomDefile");
                String idLieuString = request.getParameter("idLieu");
                long idLieu = Long.parseLong(idLieuString);
                String idCouturierString = request.getParameter("idCouturier");
                long idCouturier = Long.parseLong(idCouturierString);
                String idOrganisateurString = request.getParameter("accountID");
                long idOrganisateur = Long.parseLong(idOrganisateurString);
                String[] invitesArray = request.getParameterValues("idInvites");
                Couturier c = null;
                List<Couturier> couturiersInvites = new ArrayList<>();

                for (String idInvit : invitesArray) {
                    long idInv = Long.parseLong(idInvit);
                    c = sessionOrganisateur.rechercheCouturiers(idInv);
                    couturiersInvites.add(c);
                }
                
                if(editClicked != null) {
                    sessionOrganisateur.modifierDefile(nomDefile, dateDefile, idOrganisateur, idLieu, idCouturier, couturiersInvites, idDefile);
                    request.setAttribute("message", "Le défilé n°" + idDefile + " a bien été modifié.");
                } else if(removeClicked != null) {
                    sessionOrganisateur.supprimerDefile(idDefile);
                    request.setAttribute("message", "Le défilé n°" + idDefile + " a bien été supprimé.");
                }
                
            } catch (ParseException ex) {
                request.setAttribute("message", "Il y a eu une erreur.");
            }   
    }
    
    protected void doActionCreerAccessoire (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String nomAccessoire = request.getParameter("nomAccessoire");
            String prixAchatString = request.getParameter("prixAchat");
            Double prixAchat = Double.parseDouble(prixAchatString);
            String prixLocationString = request.getParameter("prixLocation");
            Double prixLocation = Double.parseDouble(prixLocationString);
            String categAccessoire = request.getParameter("typeAccessoire");
            String couturierIDString = request.getParameter("accountID");
            long couturierID = Long.parseLong(couturierIDString);
            
        switch (categAccessoire) {
            case "bijoux":
                String prixAssuranceString = request.getParameter("prixAssurance");
                Double prixAssurance = Double.parseDouble(prixAssuranceString);
                sessionCouturier.creerBijoux(prixAssurance, prixAchat, prixLocation, couturierID, nomAccessoire);
                request.setAttribute("message", "Le bijou " + nomAccessoire + " a bien été créé.");
                break;
            case "chaussures":
                String tailleChaussuresString = request.getParameter("tailleChaussures");
                int tailleChaussures = Integer.parseInt(tailleChaussuresString);
                sessionCouturier.creerChaussure(nomAccessoire, prixAchat, prixLocation, tailleChaussures, couturierID);
                request.setAttribute("message", "La paire de chaussures " + nomAccessoire + " a bien été créée.");
                break;
            case "vestimentaire":
                String typeVestimentaireString = request.getParameter("typeVestimentaire");
                Type typeVestimentaire;
                typeVestimentaire = Type.valueOf(typeVestimentaireString);
                sessionCouturier.creerVestimentaire(prixAchat, prixLocation, nomAccessoire, typeVestimentaire, couturierID);
                request.setAttribute("message", "L'accessoire vestimentaire " + nomAccessoire + " a bien été créé.");
                break;
            default:
                break;
        }
    }
    
    protected void doActionEditAccessoire (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String categAccessoire = request.getParameter("typeAccessoire");
            
            String editClicked = request.getParameter("submit");
            String removeClicked = request.getParameter("delete");
            
            String couturierIDString = request.getParameter("accountID");
            long couturierID = Long.parseLong(couturierIDString);
            
            switch (categAccessoire) {
                case "bijoux":
                    String idBijouString = request.getParameter("idBijou");
                    long idBijou = Long.parseLong(idBijouString);
                    String nomBijou = request.getParameter("nomBijou");
                    String prixAchatBijouString = request.getParameter("prixAchatBijou");
                    Double prixAchatBijou = Double.parseDouble(prixAchatBijouString);
                    String prixLocationBijouString = request.getParameter("prixLocationBijou");
                    Double prixLocationBijou = Double.parseDouble(prixLocationBijouString);
                    String prixAssuranceBijouString = request.getParameter("prixAssuranceBijou");
                    Double prixAssuranceBijou = Double.parseDouble(prixAssuranceBijouString);
                    if(editClicked != null) {
                        sessionCouturier.modifierBijoux(nomBijou, prixAssuranceBijou, prixAchatBijou, prixLocationBijou, idBijou);
                        request.setAttribute("message", "Le bijou " + nomBijou + " a bien été modifié.");
                    } else if(removeClicked != null) {
                        sessionCouturier.SupprimerBijoux(idBijou);
                        request.setAttribute("message", "Le bijou " + nomBijou + " a bien été supprimé.");
                    }
                    break;
                case "chaussures":
                    String idChaussuresString = request.getParameter("idChaussures");
                    long idChaussures = Long.parseLong(idChaussuresString);
                    String nomChaussures = request.getParameter("nomChaussures");
                    String prixAchatChaussuresString = request.getParameter("prixAchatChaussures");
                    Double prixAchatChaussures = Double.parseDouble(prixAchatChaussuresString);
                    String prixLocationChaussuresString = request.getParameter("prixLocationChaussures");
                    Double prixLocationChaussures = Double.parseDouble(prixLocationChaussuresString);
                    String tailleChaussuresString = request.getParameter("tailleChaussures");
                    int tailleChaussures = Integer.parseInt(tailleChaussuresString);
                    if(editClicked != null) {
                        System.out.println(idChaussures + tailleChaussures + prixAchatChaussures + prixLocationChaussures + nomChaussures);
                        sessionCouturier.modifierChaussure(tailleChaussures, prixAchatChaussures, prixLocationChaussures, nomChaussures, idChaussures);
                        request.setAttribute("message", "La paire de chaussures " + nomChaussures + " a bien été modifiée.");
                    } else if(removeClicked != null) {
                        sessionCouturier.supprimerChaussures(idChaussures);
                        request.setAttribute("message", "La paire de chaussures " + nomChaussures + " a bien été supprimée.");
                    }
                    break;
                case "vestimentaire":
                    String idVestimentaireString = request.getParameter("idVestimentaire");
                    long idVestimentaire = Long.parseLong(idVestimentaireString);
                    String nomVestimentaire = request.getParameter("nomVestimentaire");
                    String prixAchatVestimentaireString = request.getParameter("prixAchatVestimentaire");
                    Double prixAchatVestimentaire = Double.parseDouble(prixAchatVestimentaireString);
                    String prixLocationVestimentaireString = request.getParameter("prixLocationVestimentaire");
                    Double prixLocationVestimentaire = Double.parseDouble(prixLocationVestimentaireString);
                    String typeVestimentaireString = request.getParameter("typeVestimentaire");
                    Type typeVestimentaire;
                    typeVestimentaire = Type.valueOf(typeVestimentaireString);
                    if(editClicked != null) {
                        sessionCouturier.modifierVestimentaire(prixAchatVestimentaire, prixLocationVestimentaire, nomVestimentaire, typeVestimentaire, idVestimentaire);
                        request.setAttribute("message", "L'accessoire vestimentaire " + nomVestimentaire + " a bien été modifié.");
                    } else if(removeClicked != null) {
                        sessionCouturier.supprimerVestimentaire(idVestimentaire);
                        request.setAttribute("message", "L'accessoire vestimentaire " + nomVestimentaire + " a bien été supprimé.");
                    }
                    break;
                default:
                    break;
        }
    }
    
    protected void doActionCreerVetement (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            try {
                String dateVetement = request.getParameter("dateCreation");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateCreation = df.parse(dateVetement);
                String nomVetement = request.getParameter("nomVetement");
                String prixVetementString = request.getParameter("prixVetement");
                Double prixVetement = Double.parseDouble(prixVetementString);
                String tailleVetement = request.getParameter("tailleVetement");
                String idMannequinString = request.getParameter("idMannequin");
                long idMannequin = Long.parseLong(idMannequinString);
                String idDefileString = request.getParameter("idDefile");
                long idDefile = Long.parseLong(idDefileString);
                String idBijouString = request.getParameter("idBijou");
                long idBijou = Long.parseLong(idBijouString);
                String idChaussuresString = request.getParameter("idChaussures");
                long idChaussures = Long.parseLong(idChaussuresString);
                String idVestimentaireString = request.getParameter("idVestimentaire");
                long idVestimentaire = Long.parseLong(idVestimentaireString);
                String ordreString = request.getParameter("intOrdre");
                int intOrdre = Integer.parseInt(ordreString);
                String idCouturierString = request.getParameter("accountID");
                long idCouturier = Long.parseLong(idCouturierString);
                
                sessionCouturier.creerVetement(dateCreation, idCouturier, idMannequin, idBijou, idDefile, idChaussures, idVestimentaire, nomVetement, prixVetement, tailleVetement, intOrdre);
                request.setAttribute("message", "Le vêtement " + nomVetement + " a bien été créé.");
            } catch (ParseException ex) {
                request.setAttribute("message", "Il y a eu une erreur.");
            }
            
    }
    
    protected void doActionEditVetement (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            try {
                String editClicked = request.getParameter("submit");
                String removeClicked = request.getParameter("delete");
                
                String idVetementString = request.getParameter("idVetement");
                long idVetement = Long.parseLong(idVetementString);
                String dateVetement = request.getParameter("dateCreation");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateCreation = df.parse(dateVetement);
                String nomVetement = request.getParameter("nomVetement");
                String prixVetementString = request.getParameter("prixVetement");
                Double prixVetement = Double.parseDouble(prixVetementString);
                String tailleVetement = request.getParameter("tailleVetement");
                String idMannequinString = request.getParameter("idMannequin");
                long idMannequin = Long.parseLong(idMannequinString);
                String idDefileString = request.getParameter("idDefile");
                long idDefile = Long.parseLong(idDefileString);
                String idBijouString = request.getParameter("idBijou");
                long idBijou = Long.parseLong(idBijouString);
                String idChaussuresString = request.getParameter("idChaussures");
                long idChaussures = Long.parseLong(idChaussuresString);
                String idVestimentaireString = request.getParameter("idVestimentaire");
                long idVestimentaire = Long.parseLong(idVestimentaireString);
                String ordreString = request.getParameter("intOrdre");
                int intOrdre = Integer.parseInt(ordreString);
                
                if(editClicked != null) {
                    sessionCouturier.modifierVetement(nomVetement, idVetement, dateCreation, idDefile, idMannequin, idBijou, idChaussures, idVestimentaire, prixVetement, tailleVetement, intOrdre);
                    request.setAttribute("message", "Le vêtement " + nomVetement + " a bien été modifié.");
                } else if(removeClicked != null) {
                    sessionCouturier.supprimerVetement(idVetement);
                    request.setAttribute("message", "Le vêtement " + nomVetement + " a bien été supprimé.");
                }
                
            } catch (ParseException ex) {
                request.setAttribute("message", "Il y a eu une erreur.");
            }
            
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(servletDefile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(servletDefile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private GrandPublicLocal lookupGrandPublicLocal() {
        try {
            Context c = new InitialContext();
            return (GrandPublicLocal) c.lookup("java:global/Bat_blondelle_biton_defile/Bat_blondelle_biton_defile-ejb/GrandPublic!sessions.GrandPublicLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
