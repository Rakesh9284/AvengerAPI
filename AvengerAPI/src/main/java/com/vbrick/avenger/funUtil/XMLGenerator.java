/*
 * package com.vbrick.avenger.funUtil;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.FileOutputStream; import java.io.IOException; import
 * java.util.HashMap; import java.util.LinkedList; import java.util.Map; import
 * java.util.Properties; import java.util.TreeMap;
 * 
 * import javax.xml.parsers.DocumentBuilder; import
 * javax.xml.parsers.DocumentBuilderFactory; import
 * javax.xml.parsers.ParserConfigurationException; import
 * javax.xml.transform.OutputKeys; import javax.xml.transform.Transformer;
 * import javax.xml.transform.TransformerException; import
 * javax.xml.transform.TransformerFactory; import
 * javax.xml.transform.dom.DOMSource; import
 * javax.xml.transform.stream.StreamResult;
 * 
 * import org.apache.commons.io.FileUtils; import
 * org.apache.commons.io.FilenameUtils; import org.w3c.dom.Attr; import
 * org.w3c.dom.DOMImplementation; import org.w3c.dom.Document; import
 * org.w3c.dom.DocumentType; import org.w3c.dom.Element; import
 * com.vbrick.avenger.setup.Configuration; import
 * com.vbrick.avenger.setup.Setup;
 * 
 * public class XMLGenerator implements Setup {
 * 
 * private DocumentBuilderFactory docFactory; private DocumentBuilder
 * docBuilder; private Document doc; private DOMImplementation domImpl; //
 * Element elementName; FileOperation fileObj; // ReadTestData testData,
 * testData2; //private static Logger logger =
 * Logger.getLogger(XMLSuiteRunner.class); public XMLGenerator() throws
 * ParserConfigurationException, IOException { docFactory =
 * DocumentBuilderFactory.newInstance(); docBuilder =
 * docFactory.newDocumentBuilder(); doc = docBuilder.newDocument(); domImpl =
 * doc.getImplementation(); // fileObj=new FileOperation(); //
 * testData2.readConfigsheet("Config", 0); }
 * 
 * public Element rootTag(String a) { Element elementName =
 * doc.createElement(a); doc.appendChild(elementName); return elementName; }
 * 
 * public Element subTag(Element rootTagName, String childTagName) { Element
 * childElement = doc.createElement(childTagName);
 * rootTagName.appendChild(childElement); return childElement; } public void
 * setAttributes(String sattributeKey, String sattributeValue, Element tagName)
 * { Attr attr = doc.createAttribute(sattributeKey);
 * attr.setValue(sattributeValue); tagName.setAttributeNode(attr); }
 * 
 * public void createXML(ExcelRead testData,String sSheetName) throws
 * TransformerException, IOException {
 * 
 * String suiteName =
 * FilenameUtils.removeExtension(Configuration.getTestCaseFile()).split("/")[4];
 * System.out.println("Sheet name"+sSheetName+"===="+suiteName); DocumentType
 * doctype = domImpl.createDocumentType("doctype", "suite",
 * "http://testng.org/testng-1.0.dtd"); Element suiteTag = rootTag("suite");
 * setAttributes("name", suiteName, suiteTag); setAttributes("thread-count",
 * "10", suiteTag); setAttributes("parallel", "classes", suiteTag); Element
 * listnersTag = subTag(suiteTag, "listeners"); Element listnerTag1 =
 * subTag(listnersTag, "listener"); Element listnerTag2 = subTag(listnersTag,
 * "listener"); Element listnerTag3 = subTag(listnersTag, "listener");
 * setAttributes("class-name", "org.uncommons.reportng.HTMLReporter",
 * listnerTag1); setAttributes("class-name",
 * "org.uncommons.reportng.JUnitXMLReporter", listnerTag2);
 * setAttributes("class-name", "com.vbrick.avenger.funUtil.CustomReporter",
 * listnerTag3); Element testTag = subTag(suiteTag, "test");
 * setAttributes("name", suiteName, testTag); Element classesTag =
 * subTag(testTag, "classes");
 * System.out.println("The test date object----"+testData);
 * System.out.println(" Sheet Name ::::::: "+sSheetName); TreeMap<String,
 * LinkedList> xmlMap = testData.readTestCaseExcel(sSheetName); for
 * (Map.Entry<String, LinkedList> mapIterator : xmlMap.entrySet()) {
 * 
 * Element classTag = subTag(classesTag,"class"); setAttributes("name",
 * mapIterator.getKey(), classTag); Element methodTag = subTag(classTag,
 * "methods"); LinkedList methodArray = mapIterator.getValue(); for (int i = 0;
 * i < methodArray.size(); i++) { String arrayValues = (String)
 * methodArray.get(i); String methodName = arrayValues.split("@!@")[0]; String
 * flag = arrayValues.split("@!@")[1]; if (flag.equalsIgnoreCase("y")) { Element
 * includeTag = subTag(methodTag,"include"); setAttributes("name", methodName,
 * includeTag); } else { Element excludeTag = subTag(methodTag,"exclude");
 * setAttributes("name", methodName, excludeTag); } } }
 * 
 * TransformerFactory transformerFactory = TransformerFactory.newInstance();
 * Transformer transformer = transformerFactory.newTransformer();
 * transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //
 * transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, //
 * doctype.getPublicId());
 * transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
 * doctype.getSystemId()); DOMSource source = new DOMSource(doc); //
 * if(fileObj.deleteXMLFile(createXMlFile)) { File xmlFile = new
 * File(createXMlFile); if(xmlFile.exists()) { StreamResult result = new
 * StreamResult(new File(suiteName+".xml")); transformer.transform(source,
 * result); System.out.println("File Created"); } else
 * System.out.println("File Created ::::: "); // testData.closeObjects(); // } }
 * 
 * public void setEmail(ExcelRead testData2,String... ar) throws IOException,
 * ArrayIndexOutOfBoundsException { if (ar.length > 0) {
 * System.out.println("@@@@@" + ar[1]); HashMap<String, String> emailMap =
 * testData2.readConfigsheet("Config", 0, 1);
 * System.out.println("EmailMAP $$$$ " + emailMap.get("From")); Properties pop =
 * new Properties(); pop.load(new
 * FileInputStream(FileOperation.getFilePath(EMAILCONFIG))); pop.put("UserName",
 * emailMap.get("From")); String recipient = emailMap.get("To"); String
 * recipientArray[] = recipient.split(","); if (recipientArray.length == 1) {
 * pop.put("recipients", recipientArray[0]); } else { for (int i = 0; i <
 * recipientArray.length; i++) { pop.put("recipients", recipientArray[i]); } }
 * pop.put("bccrecipients", emailMap.get("Bcc")); pop.put("sender",
 * emailMap.get("From")); FileOutputStream output = new
 * FileOutputStream(FileOperation.getFilePath(EMAILCONFIG)); pop.store(output,
 * null); } } public static void main(String args[]) throws
 * ParserConfigurationException, TransformerException, IOException { try {
 * FileUtils.deleteDirectory(new
 * File(FileOperation.getFilePath("/test-output")));
 * FileUtils.deleteDirectory(new
 * File(FileOperation.getFilePath(OUTPUT_ZIP_FILE)));
 * 
 * } catch (Exception e) { } ExcelRead testData = new ExcelRead(); ExcelRead
 * testData2 = new ExcelRead(); XMLGenerator xml = new XMLGenerator(); //
 * xml.createXML(testData,args[0]); try {
 * System.out.println("Sheet name from command line 0...."+args[0]);
 * //System.out.println("Sheet name from command line 1...."+args[1]);
 * xml.createXML(testData,args[0]); // xml.setEmail(testData2,args[1]); } catch
 * (ArrayIndexOutOfBoundsException e) {
 * System.err.println("No Config File Set"); }
 * 
 * try { // System.out.println("Sheet name from command line 0...."+args[0]);
 * System.out.println("Sheet name from command line 1...."+args[1]);
 * //xml.createXML(testData,args[0]); xml.setEmail(testData2,args[1]); } catch
 * (ArrayIndexOutOfBoundsException e) {
 * System.err.println("Invalid file path from pom.xml"); }
 * 
 * 
 * //FileUtils.deleteDirectory(arg0); File Folder = new File("test-output");
 * String[] files=Folder.list();
 * 
 * for(String s:files){
 * 
 * File currentFile=new File(FileOperation.getFilePath("/test-output"),s);
 * String path=FileOperation.getFilePath("/test-output");
 * System.out.println("Path :::: "+path); currentFile.delete();
 * //System.out.println("File Deleted SucessFully :::: "); }
 * 
 * try{ if(Folder.exists()){ Folder.delete();
 * System.out.println("File Deleted SucessFully"); } else
 * System.out.println("File Exists"); } catch(Exception e) {
 * e.printStackTrace(); } } }
 */