package ir.maktabsharif.userprofile.util;
import ir.maktabsharif.userprofile.model.Permission;
import ir.maktabsharif.userprofile.model.UserRole;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXml {
    private static List<Permission> permissionList = new ArrayList<>();
    private static List<UserRole> userRoleList = new ArrayList<>();

    public static List<Permission> getPermissionList() {
        return permissionList;
    }

    public static List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public static void readXml() {
        try {
            File file = new File("D:\\UserProfile\\src\\main\\resources\\application_config.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            Element documentElement = doc.getDocumentElement();

            NodeList rolesList = documentElement.getElementsByTagName("roles");

            Element roleElement = (Element) rolesList.item(0);


            NodeList childNodes = roleElement.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {

                Node role = childNodes.item(i);

                if (role.getNodeType() == Node.ELEMENT_NODE) {

                    Element nodeElement = (Element) role;
                    String roleName = nodeElement.getNodeName();

                    NodeList permissionsList = nodeElement.getElementsByTagName("permissions");

                    Element permissionElement = (Element) permissionsList.item(0);

                    NodeList permissionNodeList = permissionElement.getChildNodes();

                    List<String> permissionName = new ArrayList<>();

                    for (int j = 0; j < permissionNodeList.getLength(); j++) {
                        Node permissionNode = permissionNodeList.item(j);

                        if (permissionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element permissionElementNode = (Element) permissionNode;

                            String permissionTextContent = permissionElementNode.getTextContent().trim();
                            permissionName.add(permissionTextContent);
                        }
                    }
                    List<Permission> permissionList = makeListOfPermissions(permissionName);
                    UserRole userRole = UserRole.builder().role(roleName).permissions(permissionList).build();
                    userRoleList.add(userRole);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Permission> makeListOfPermissions(List<String> permissionsName) {
        List<Permission> result = new ArrayList<>();

        outer:
        for (String permissionName : permissionsName) {
            inner:
            for (Permission item : permissionList) {
                if (permissionName.equals(item.getPermission())) {
                    result.add(item);
                    continue outer;
                }
            }
            Permission permission = Permission.builder().permission(permissionName).build();
            result.add(permission);
            permissionList.add(permission);
        }

        return result;
    }

}
