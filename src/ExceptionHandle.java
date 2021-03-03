import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExceptionHandle {
	
	static FileReader fread = null;
	static Properties props = new Properties();
	
	static {
		try {
			fread = new FileReader("C:\\Users\\Anup\\eclipse-workspace\\ExceptionHandling\\src\\ActionPerform.properties");
			props.load(fread);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void handleException(String projectName, String moduleName, Exception ex) {		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder  builder = factory.newDocumentBuilder();
			Document document = builder.parse("C:\\Users\\Anup\\eclipse-workspace\\ExceptionHandling\\src\\ExceptionHandle.xml");
			
			Element rootelement = document.getDocumentElement();
			System.out.println("\n" + projectName);
			
			NodeList moduleNodes = rootelement.getChildNodes();
			
			for(int i=0 ; i<moduleNodes.getLength() ; i++) 
			{
				Node moduleNode = moduleNodes.item(i);
				if(moduleNode.getNodeType() == Node.ELEMENT_NODE && ((Element)moduleNode).getAttribute("name").equals(moduleName)) 
				{
					System.out.println("\nModule Name is: " + ((Element)moduleNode).getAttribute("name"));
					NodeList exceptionNodes = moduleNode.getChildNodes();
					//under module
					for(int j=0 ; j<exceptionNodes.getLength() ; j++) 
					{
						Node exceptionNode = exceptionNodes.item(j);
						//checking module
						if(exceptionNode.getNodeType() == Node.ELEMENT_NODE) 
						{
							String exceptionName = ((Element)exceptionNode).getNodeName();
							//checking Exception
							if(exceptionName.equals(ex.getClass().getSimpleName())) 
							{
								//At Actions
								NodeList actionsNode = exceptionNode.getChildNodes();
								
								for(int l=0 ; l<actionsNode.getLength() ; l++)
								{
									Node actionsN = actionsNode.item(l);								
									if(actionsN.getNodeType() == Node.ELEMENT_NODE) 
									{
										NodeList actionNodes = actionsN.getChildNodes();
										//under Actions
										for(int k=0 ; k<actionNodes.getLength() ; k++) 
										{
											//At action
											Node actionN = actionNodes.item(k);
											if(actionN.getNodeType() == Node.ELEMENT_NODE)
											{											
												Map<String, String> nvPair = new HashMap<String, String>();
												
												Element actionElement = (Element) actionN;
												NamedNodeMap actionAttribute = actionElement.getAttributes();
												
												for (int m = 0; m < actionAttribute.getLength(); ++m)
												{
												    Node attr = actionAttribute.item(m);
												    nvPair.put(attr.getNodeName(), attr.getNodeValue());
												}
												
												String actionName = nvPair.get("name");
												String className = props.getProperty(actionName);
												Action action = (Action) Class.forName(className).newInstance();
												action.takeAction(nvPair);
												
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
}