import java.sql.*;
import java.util.*;

class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "database";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

class Owner {
    int ownerId;
    String ownerName;
    String ownerPhone;
}

class Site {
    int siteId, length, width;
    String siteType, siteStatus;
    Integer ownerId;
}

class OwnerSiteInfo {
    int ownerId;
    String ownerName;
    String ownerPhone;
    Integer siteId;
    String siteType;
}

class OwnerDAO {

    public void addOwner(Owner o) throws Exception {
        String sql = "INSERT INTO owner VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, o.ownerId);
            ps.setString(2, o.ownerName);
            ps.setString(3, o.ownerPhone);
            ps.executeUpdate();
        }
    }

    public List<OwnerSiteInfo> getAllOwners() throws Exception {
        List<OwnerSiteInfo> list = new ArrayList<>();

        String sql = """
            SELECT o.owner_id, o.owner_name, o.owner_phone,
                   s.site_id, s.site_type
            FROM owner o
            LEFT JOIN sites s ON o.owner_id = s.owner_id
            ORDER BY o.owner_id
        """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                OwnerSiteInfo info = new OwnerSiteInfo();
                info.ownerId = rs.getInt("owner_id");
                info.ownerName = rs.getString("owner_name");
                info.ownerPhone = rs.getString("owner_phone");

                int siteId = rs.getInt("site_id");
                if (rs.wasNull()) info.siteId = null;
                else {
                    info.siteId = siteId;
                    info.siteType = rs.getString("site_type");
                }
                list.add(info);
            }
        }
        return list;
    }
}

class SiteDAO {
    public Site getSite(int id) throws Exception {
        String sql = "SELECT * FROM sites WHERE site_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Site s = new Site();
                s.siteId = rs.getInt("site_id");
                s.length = rs.getInt("length_ft");
                s.width = rs.getInt("width_ft");
                s.siteType = rs.getString("site_type");
                s.siteStatus = rs.getString("site_status");
                return s;
            }
        }
        return null;
    }

    public void assignSite(int siteId, int ownerId, String type) throws Exception {
        String sql = """
            UPDATE sites
            SET owner_id=?, site_status='Occupied', site_type=?
            WHERE site_id=?
        """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ps.setString(2, type);
            ps.setInt(3, siteId);
            ps.executeUpdate();
        }
    }
}

public class LayoutMaintenance {

    static double calculateMaintenance(Site s) {
        int area = s.length * s.width;
        return s.siteStatus.equalsIgnoreCase("Open") ? area * 6 : area * 9;
    }

    static void addMaintenance(Site s, int month, int year) throws Exception {
        double amount = calculateMaintenance(s);

        String sql = """
            INSERT INTO maintenance
            (m_id, site_id, m_month, m_year, amount, paid_status)
            VALUES (?, ?, ?, ?, ?, 'Pending')
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, new Random().nextInt(100000));
            ps.setInt(2, s.siteId);
            ps.setInt(3, month);
            ps.setInt(4, year);
            ps.setDouble(5, amount);
            ps.executeUpdate();
        }
    }

    static void viewPending() throws Exception {
        String sql = """
            SELECT * FROM maintenance WHERE paid_status='Pending'
        """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Site: " + rs.getInt("site_id") +
                        " | Month: " + rs.getInt("m_month") +
                        " | Year: " + rs.getInt("m_year") +
                        " | Amount: " + rs.getDouble("amount"));
            }
        }
    }

    static void markPaid(int siteId, int month, int year) throws Exception {
        String sql = """
            UPDATE maintenance
            SET paid_status='Paid', paid_date=CURRENT_DATE
            WHERE site_id=? AND m_month=? AND m_year=?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, siteId);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.executeUpdate();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        OwnerDAO ownerDAO = new OwnerDAO();
        SiteDAO siteDAO = new SiteDAO();

        while (true) {
            System.out.println("""
                \n---- Layout Maintenance ----
                1. Add Owner
                2. View Owners
                3. Assign Site to Owner
                4. Add Monthly Maintenance
                5. View Pending Maintenance
                6. Mark Maintenance Paid
                7. Exit
            """);

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    Owner o = new Owner();
                    System.out.print("Owner ID: ");
                    o.ownerId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    o.ownerName = sc.nextLine();
                    System.out.print("Phone: ");
                    o.ownerPhone = sc.nextLine();
                    ownerDAO.addOwner(o);
                }

                case 2 -> {
                    for (OwnerSiteInfo o : ownerDAO.getAllOwners()) {
                        if (o.siteId == null)
                            System.out.println(o.ownerName + " | No Site");
                        else
                            System.out.println(o.ownerName + " | Site: " + o.siteId + " | " + o.siteType);
                    }
                }

                case 3 -> {
                    System.out.print("Site ID: ");
                    int siteId = sc.nextInt();
                    System.out.print("Owner ID: ");
                    int ownerId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Type (Villa/Apartment/Independent): ");
                    String type = sc.nextLine();
                    siteDAO.assignSite(siteId, ownerId, type);
                }

                case 4 -> {
                    System.out.print("Site ID: ");
                    int id = sc.nextInt();
                    Site s = siteDAO.getSite(id);
                    System.out.print("Month: ");
                    int m = sc.nextInt();
                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    addMaintenance(s, m, y);
                }

                case 5 -> viewPending();

                case 6 -> {
                    System.out.print("Site ID: ");
                    int id = sc.nextInt();
                    System.out.print("Month: ");
                    int m = sc.nextInt();
                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    markPaid(id, m, y);
                }

                case 7 -> System.exit(0);
            }
        }
    }
}