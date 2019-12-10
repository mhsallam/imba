package com.mhsallam.imba.bootstrap;

import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.models.entity.*;
import com.mhsallam.imba.repositories.RoleRepository;
import com.mhsallam.imba.services.AdminAreasService;
import com.mhsallam.imba.services.OrganisationService;
import com.mhsallam.imba.services.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBootstraper implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(DataBootstraper.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AdminAreasService adminAreasService;

    @Autowired
    OrganisationService organisationService;

    @Override
    public void run(String... strings) throws Exception {
        bootstrapUsers();
        bootstrapGeoAdmins();
        bootstrapOrganisations();
    }

    private void bootstrapOrganisations() throws Exception {
        organisationService.create(new Org("Save the Children", "SCI", "INGO"));
        organisationService.create(new Org("Al-hikma Al-yamania Association for Charity", "HYAC", "NNGO"));
        organisationService.create(new Org("Governorate Health Office", "GHO", "GOV"));
        organisationService.create(new Org("International Organization for Migration", "IOM", "UN"));
        organisationService.create(new Org("Intersos - Humanitarian Aid Organization", "INTERSOS", "INGO"));
        organisationService.create(new Org("Danish Refugee Council", "DRC", "INGO"));
        organisationService.create(new Org("Family Counseling and Development Foundation", "FCDF", "NNGO"));
        organisationService.create(new Org("Ministry of Social Affairs & Labour", "MOSAL", "GOV"));
        organisationService.create(new Org("Office of the United Nations High Commissioner for Human Rights", "OHCHR", "UN"));
        organisationService.create(new Org("Yemen Women Union", "YWU", "NNGO"));
        organisationService.create(new Org("Yemen Executive Mine Action Center", "YEMAC", "NNGO"));
        organisationService.create(new Org("Social Fund for Development", "SFD", "NNGO"));
        organisationService.create(new Org("Oxford Committee for Famine Relief GB", "OXFAM", "INGO"));
        organisationService.create(new Org("United Nations High Commissioner for Refugees", "UNHCR", "UN"));
        organisationService.create(new Org("Nahda Makers Organization", "NMO", "NNGO"));
        organisationService.create(new Org("Relief International", "RI", "INGO"));
        organisationService.create(new Org("World Health Organization", "WHO", "UN"));
        organisationService.create(new Org("Public Works Project", "PWP", "NNGO"));
        organisationService.create(new Org("Action Contre La Faim (Action Against Hunger) - France", "ACF-F", "INGO"));
    }

    private void bootstrapGeoAdmins() throws ConflictException, Exception {
        adminAreasService.addAdmin1(new Admin1("Abyan", "YE12"));
        adminAreasService.addAdmin1(new Admin1("Ad Dali'", "YE30"));
        adminAreasService.addAdmin1(new Admin1("Aden", "YE24"));
        adminAreasService.addAdmin1(new Admin1("Al Bayda", "YE14"));
        adminAreasService.addAdmin1(new Admin1("Al Hodeidah", "YE18"));
        adminAreasService.addAdmin1(new Admin1("Al Jawf", "YE16"));
        adminAreasService.addAdmin1(new Admin1("Al Maharah", "YE28"));
        adminAreasService.addAdmin1(new Admin1("Al Mahwit", "YE27"));
        adminAreasService.addAdmin1(new Admin1("Amran", "YE29"));
        adminAreasService.addAdmin1(new Admin1("Dhamar", "YE20"));
        adminAreasService.addAdmin1(new Admin1("Hadramawt", "YE19"));
        adminAreasService.addAdmin1(new Admin1("Hajjah", "YE17"));
        adminAreasService.addAdmin1(new Admin1("Ibb", "YE11"));
        adminAreasService.addAdmin1(new Admin1("Lahj", "YE25"));
        adminAreasService.addAdmin1(new Admin1("Ma'rib", "YE26"));
        adminAreasService.addAdmin1(new Admin1("Raymah", "YE31"));
        adminAreasService.addAdmin1(new Admin1("Sa'dah", "YE22"));
        adminAreasService.addAdmin1(new Admin1("Sana'a", "YE23"));
        adminAreasService.addAdmin1(new Admin1("Sana'a City", "YE13"));
        adminAreasService.addAdmin1(new Admin1("Shabwah", "YE21"));
        adminAreasService.addAdmin1(new Admin1("Socotra", "YE32"));
        adminAreasService.addAdmin1(new Admin1("Ta'iz", "YE15"));

        adminAreasService.addAdmin2(new Admin2("Ahwar", "YE1209", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Al Mahfad", "YE1201", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Al Wadi'", "YE1208", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Jayshan", "YE1203", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Khanfar", "YE1211", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Lawdar", "YE1204", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Mudiyah", "YE1202", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Rassd", "YE1206", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Sarar", "YE1207", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Sibah", "YE1205", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Zinjibar", "YE1210", "YE12"));
        adminAreasService.addAdmin2(new Admin2("Ad Dali'", "YE3006", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Al Azariq", "YE3008", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Al Hasayn", "YE3005", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Al Husha", "YE3009", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Ash Shu'ayb", "YE3004", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Damt", "YE3002", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Jahaf", "YE3007", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Juban", "YE3001", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Qa'tabah", "YE3003", "YE30"));
        adminAreasService.addAdmin2(new Admin2("Al Burayqah", "YE2404", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Al Mansurah", "YE2403", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Al Mu'alla", "YE2406", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Ash Shaykh Othman", "YE2402", "YE24"));
        adminAreasService.addAdmin2(new Admin2("At Tawahi", "YE2405", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Dar Sa'd", "YE2401", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Khur Maksar", "YE2408", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Kritar - Sirah", "YE2407", "YE24"));
        adminAreasService.addAdmin2(new Admin2("Al Arsh", "YE1416", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Al Bayda", "YE1410", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Al Bayda City", "YE1409", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Al Malajim", "YE1420", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Al Quraishyah", "YE1414", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Ar Ryashyyah", "YE1418", "YE14"));
        adminAreasService.addAdmin2(new Admin2("As Sawadiyah", "YE1411", "YE14"));
        adminAreasService.addAdmin2(new Admin2("As Sawma'ah", "YE1404", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Ash Sharyah", "YE1419", "YE14"));
        adminAreasService.addAdmin2(new Admin2("At Taffah", "YE1407", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Az Zahir", "YE1405", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Dhi Na'im", "YE1406", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Maswarah", "YE1403", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Mukayras", "YE1408", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Nati'", "YE1402", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Nu'man", "YE1401", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Rada'", "YE1413", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Radman", "YE1412", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Sabah", "YE1417", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Wald Rabi'", "YE1415", "YE14"));
        adminAreasService.addAdmin2(new Admin2("Ad Dohi", "YE1809", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Ad Durayhimi", "YE1814", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Hali", "YE1823", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Hawak", "YE1821", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Hujjaylah", "YE1811", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Jarrahi", "YE1825", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Khukhah", "YE1820", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Mansuriyah", "YE1816", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Marawi'ah", "YE1813", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Mighlaf", "YE1808", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Mina", "YE1822", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Munirah", "YE1805", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Qanawis", "YE1806", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Alluhayah", "YE1802", "YE18"));
        adminAreasService.addAdmin2(new Admin2("As Salif", "YE1804", "YE18"));
        adminAreasService.addAdmin2(new Admin2("As Sukhnah", "YE1815", "YE18"));
        adminAreasService.addAdmin2(new Admin2("At Tuhayta", "YE1826", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Az Zaydiah", "YE1807", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Az Zuhrah", "YE1801", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Bajil", "YE1810", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Bayt Al Faqih", "YE1817", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Bura'", "YE1812", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Hays", "YE1819", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Jabal Ras", "YE1818", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Kamaran", "YE1803", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Zabid", "YE1824", "YE18"));
        adminAreasService.addAdmin2(new Admin2("Al Ghayl", "YE1608", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Hazm", "YE1605", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Humaydat", "YE1602", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Khalaq", "YE1609", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Maslub", "YE1607", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Matammah", "YE1603", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Mutun", "YE1606", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Az Zahir", "YE1604", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Barat Al Anan", "YE1610", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Khab wa Ash Sha'f", "YE1601", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Kharab Al Marashi", "YE1612", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Rajuzah", "YE1611", "YE16"));
        adminAreasService.addAdmin2(new Admin2("Al Ghaydhah", "YE2804", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Al Masilah", "YE2806", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Haswin", "YE2809", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Hat", "YE2802", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Hawf", "YE2803", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Man'ar", "YE2805", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Qishn", "YE2808", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Sayhut", "YE2807", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Shahin", "YE2801", "YE28"));
        adminAreasService.addAdmin2(new Admin2("Al Khabt", "YE2704", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Al Mahwit", "YE2709", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Al Mahwit City", "YE2708", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Ar Rujum", "YE2703", "YE27"));
        adminAreasService.addAdmin2(new Admin2("At Tawilah", "YE2702", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Bani Sa'd", "YE2707", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Hufash", "YE2706", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Melhan", "YE2705", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Shibam Kawkaban", "YE2701", "YE27"));
        adminAreasService.addAdmin2(new Admin2("Al Ashah", "YE2903", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Al Madan", "YE2906", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Amran", "YE2915", "YE29"));
        adminAreasService.addAdmin2(new Admin2("As Sawd", "YE2914", "YE29"));
        adminAreasService.addAdmin2(new Admin2("As Sudah", "YE2913", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Bani Surim", "YE2920", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Dhibain", "YE2909", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Dhulaymat Habur", "YE2908", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Eyal Surayh", "YE2918", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Harf Sufyan", "YE2901", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Huth", "YE2902", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Jabal Eyal Yazid", "YE2912", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Khamir", "YE2919", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Kharif", "YE2910", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Maswar", "YE2916", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Qaflat Odhar", "YE2904", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Raydah", "YE2911", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Shaharah", "YE2905", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Suwayr", "YE2907", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Thula", "YE2917", "YE29"));
        adminAreasService.addAdmin2(new Admin2("Al Hada", "YE2001", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Al Manar", "YE2012", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Ans", "YE2010", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Dhamar City", "YE2008", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Dwran Anis", "YE2011", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Jabal Ash sharq", "YE2003", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Jahran", "YE2002", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Maghrib Ans", "YE2004", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Mayfa'at Ans", "YE2009", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Otmah", "YE2005", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Wusab Al Aali", "YE2006", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Wusab As Safil", "YE2007", "YE20"));
        adminAreasService.addAdmin2(new Admin2("Ad Dis", "YE1914", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Ad Dulay'ah", "YE1922", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Al Abr", "YE1906", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Al Mukalla", "YE1926", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Al Mukalla City", "YE1927", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Al Qaff", "YE1903", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Al Qatn", "YE1907", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Amd", "YE1921", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Ar Raydah wa Qussay'ar", "YE1913", "YE19"));
        adminAreasService.addAdmin2(new Admin2("As Sawm", "YE1912", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Ash Shihr", "YE1915", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Brum Mayf'ah", "YE1925", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Daw'an", "YE1918", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Ghayl Bawazir", "YE1917", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Ghayl bin Yamin", "YE1916", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Hajar", "YE1924", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Hajar As Say'ar", "YE1905", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Haridah", "YE1928", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Rakhyah", "YE1920", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Rumah", "YE1901", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Sah", "YE1909", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Sayun", "YE1910", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Shibam", "YE1908", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Tarim", "YE1911", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Thamud", "YE1902", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Wadi Al Ayn", "YE1919", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Yab'uth", "YE1923", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Zamakh wa Manwokh", "YE1904", "YE19"));
        adminAreasService.addAdmin2(new Admin2("Abs", "YE1704", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Aflah Al Yaman", "YE1714", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Aflah Ash Sham", "YE1710", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Al Jamimah", "YE1708", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Al Maghrabah", "YE1717", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Al Mahabishah", "YE1715", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Al Miftah", "YE1716", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Ash shaghadirah", "YE1725", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Ash Shahil", "YE1721", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Aslam", "YE1712", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Bakil Al Mir", "YE1701", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Bani Al Awam", "YE1727", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Bani Qays", "YE1724", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Hajjah", "YE1729", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Hajjah City", "YE1728", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Harad", "YE1702", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Hayran", "YE1705", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Khayran Al Muharraq", "YE1711", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Ku'aydinah", "YE1722", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Kuhlan Afar", "YE1718", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Kuhlan Ash Sharaf", "YE1709", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Kushar", "YE1707", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Mabyan", "YE1720", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Midi", "YE1703", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Mustaba", "YE1706", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Najrah", "YE1726", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Qafl Shammar", "YE1713", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Qarah", "YE1731", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Sharas", "YE1719", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Wadrah", "YE1723", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Washhah", "YE1730", "YE17"));
        adminAreasService.addAdmin2(new Admin2("Adh Dhihar", "YE1119", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Al Makhadir", "YE1107", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Al Mashannah", "YE1118", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Al Odayn", "YE1111", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Al Qafr", "YE1101", "YE11"));
        adminAreasService.addAdmin2(new Admin2("An Nadirah", "YE1104", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Ar Radmah", "YE1103", "YE11"));
        adminAreasService.addAdmin2(new Admin2("As Sabrah", "YE1114", "YE11"));
        adminAreasService.addAdmin2(new Admin2("As Saddah", "YE1106", "YE11"));
        adminAreasService.addAdmin2(new Admin2("As Saiyani", "YE1115", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Ash Sha'ir", "YE1105", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Ba'dan", "YE1113", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Dhi As Sufal", "YE1116", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Far' Al Odayn", "YE1110", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Hazm Al Odayn", "YE1109", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Hobeish", "YE1108", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Ibb", "YE1120", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Jiblah", "YE1112", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Mudhaykhirah", "YE1117", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Yarim", "YE1102", "YE11"));
        adminAreasService.addAdmin2(new Admin2("Al Had", "YE2501", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Hawtah", "YE2514", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Madaribah Wa Al Aarah", "YE2513", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Maflahi", "YE2503", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Malah", "YE2508", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Maqatirah", "YE2512", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Musaymir", "YE2509", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Qubaytah", "YE2510", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Habil Jabr", "YE2505", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Halmin", "YE2506", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Radfan", "YE2507", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Tuban", "YE2515", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Tur Al Bahah", "YE2511", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Yafi'", "YE2502", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Yahr", "YE2504", "YE25"));
        adminAreasService.addAdmin2(new Admin2("Al Abdiyah", "YE2611", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Al Jubah", "YE2607", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Bidbadah", "YE2605", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Harib", "YE2609", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Harib Al Qaramish", "YE2604", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Jabal Murad", "YE2614", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Ma'rib", "YE2613", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Ma'rib City", "YE2612", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Madghal Al Jid'an", "YE2603", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Mahliyah", "YE2610", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Majzar", "YE2601", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Raghwan", "YE2602", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Rahabah", "YE2608", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Sirwah", "YE2606", "YE26"));
        adminAreasService.addAdmin2(new Admin2("Al Ja'fariyyah", "YE3106", "YE31"));
        adminAreasService.addAdmin2(new Admin2("Al Jabin", "YE3103", "YE31"));
        adminAreasService.addAdmin2(new Admin2("As Salafiyyah", "YE3102", "YE31"));
        adminAreasService.addAdmin2(new Admin2("Bilad Atta'am", "YE3101", "YE31"));
        adminAreasService.addAdmin2(new Admin2("Kusmah", "YE3105", "YE31"));
        adminAreasService.addAdmin2(new Admin2("Mazhar", "YE3104", "YE31"));
        adminAreasService.addAdmin2(new Admin2("Adh Dhahir", "YE2207", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Al Hashwah", "YE2213", "YE22"));
        adminAreasService.addAdmin2(new Admin2("As Safra", "YE2212", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Baqim", "YE2201", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Ghamr", "YE2204", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Haydan", "YE2208", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Kitaf wa Al Boqa'", "YE2214", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Majz", "YE2210", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Monabbih", "YE2203", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Qatabir", "YE2202", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Razih", "YE2205", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Sa'dah", "YE2215", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Sahar", "YE2211", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Saqin", "YE2209", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Shada'a", "YE2206", "YE22"));
        adminAreasService.addAdmin2(new Admin2("Al Haymah Ad Dakhiliyah", "YE2308", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Al Haymah Al Kharijiyah", "YE2309", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Al Hissn", "YE2315", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Arhab", "YE2302", "YE23"));
        adminAreasService.addAdmin2(new Admin2("At Tyal", "YE2313", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Bani Dabyan", "YE2314", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Bani Hushaysh", "YE2304", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Bani Matar", "YE2307", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Bilad Ar Rus", "YE2306", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Hamdan", "YE2301", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Jihanah", "YE2316", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Khawlan", "YE2312", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Manakhah", "YE2310", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Nihm", "YE2303", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Sa'fan", "YE2311", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Sanhan wa Bani Bahlul", "YE2305", "YE23"));
        adminAreasService.addAdmin2(new Admin2("Al Wehdah", "YE1306", "YE13"));
        adminAreasService.addAdmin2(new Admin2("As Sab'in", "YE1305", "YE13"));
        adminAreasService.addAdmin2(new Admin2("As Safiyah", "YE1304", "YE13"));
        adminAreasService.addAdmin2(new Admin2("At Tahrir", "YE1307", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Ath Thawrah", "YE1309", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Azaal", "YE1303", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Bani Al Harith", "YE1310", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Ma'in", "YE1308", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Old City", "YE1301", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Sana'a City Outskirts - Hamdan", "YE1319", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Sana'a City Outskirts -Sanhan wa Bani Bahlul", "YE1324", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Shu'ub", "YE1302", "YE13"));
        adminAreasService.addAdmin2(new Admin2("Ar Rawdah", "YE2115", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Arma'a", "YE2104", "YE21"));
        adminAreasService.addAdmin2(new Admin2("As Sa'id", "YE2112", "YE21"));
        adminAreasService.addAdmin2(new Admin2("At Talh", "YE2102", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Ataq", "YE2113", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Ayn", "YE2106", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Bayhan", "YE2107", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Dahr", "YE2101", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Habban", "YE2114", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Hatib", "YE2111", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Jardan", "YE2103", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Markhah Al Olya", "YE2108", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Markhah As Sufla", "YE2109", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Mayfa'ah", "YE2116", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Nisab", "YE2110", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Osaylan", "YE2105", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Radum", "YE2117", "YE21"));
        adminAreasService.addAdmin2(new Admin2("Hadibu", "YE3201", "YE32"));
        adminAreasService.addAdmin2(new Admin2("Qalansiyah wa Abd Al Kuri", "YE3202", "YE32"));
        adminAreasService.addAdmin2(new Admin2("Al Ma'afer", "YE1521", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Makha", "YE1505", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Mawasit", "YE1522", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Misrakh", "YE1511", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Mudhaffar", "YE1517", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Qahirah", "YE1518", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Al Wazi'yah", "YE1515", "YE15"));
        adminAreasService.addAdmin2(new Admin2("As Silw", "YE1513", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Ash Shamayatayn", "YE1514", "YE15"));
        adminAreasService.addAdmin2(new Admin2("At Ta'iziyah", "YE1520", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Dhubab", "YE1506", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Dimnat Khadir", "YE1512", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Hayfan", "YE1516", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Jabal Habashi", "YE1508", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Maqbanah", "YE1504", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Mashr'ah Wa Hadnan", "YE1509", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Mawiyah", "YE1501", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Mawza'", "YE1507", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Sabir Al Mawadim", "YE1510", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Salah", "YE1519", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Sami'", "YE1523", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Shar'ab Ar Rawnah", "YE1503", "YE15"));
        adminAreasService.addAdmin2(new Admin2("Shar'ab As Salam", "YE1502", "YE15"));
    }

    private void bootstrapUsers() {
        //only for persisted databases
        logger.info("Seeding initial application roles..");
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role userRole = createRoleIfNotFound("ROLE_USER");

        if(userService.findAll().isEmpty()) {
            logger.info("Seeding initial users data..");

            logger.info("Seeding Administrator..");
            User admin = new User("admin@immap.org", "administrator", "Administrator");
            admin.setAvatar("/avatars/admin.png");
            admin.setEnabled(true);
            userService.register(admin, true);

            logger.info("Seeding Mohammed Sallam..");
            User user = new User("user@immap.org", "password", "Mohammed Sallam");
            user.setAvatar("/avatars/user.png");
            user.setEnabled(true);
            userService.register(user, false);
        }
    }

    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
