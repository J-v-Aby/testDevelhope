package develhope.portlet;

import com.fasterxml.jackson.databind.util.JSONPObject;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.util.PropsUtil;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

//import org.jsoup.JSONObject;
import org.osgi.service.component.annotations.Component;
import develhope.constants.DevelhopePortletKeys;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author pc
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Develhope",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DevelhopePortletKeys.DEVELHOPE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DevelhopePortlet extends MVCPortlet {

	@ProcessAction(name = "test")
	public void fetchJoke(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		try {

			String url = "https://v2.jokeapi.dev/joke/Programming";
			JSONObject data = fetchDataFromApi(url);

			System.out.println(data);
			System.out.println(data.getString("setup"));
			System.out.println(data.getString("delivery"));

			if (data.has("setup") && data.has("delivery")) {
				actionRequest.setAttribute("setupTextId", data.getString("setup"));
				actionRequest.setAttribute("deliveryTextId", data.getString("delivery"));
			} else {

				System.out.println("Proprietà non esistente!");
				actionRequest.setAttribute("alertMessage", "Proprietà non esistente!");
			}

		} catch (Exception e) {

			System.err.println("Errore durante la chiamata HTTP: " + e.getMessage());
            actionRequest.setAttribute("alertMessage", "Proprietà non esistente!");
			}

	}
		private JSONObject fetchDataFromApi(String apiUrl) throws IOException, JSONException {
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			try {
				URL url = new URL(apiUrl);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				return JSONFactoryUtil.createJSONObject(response.toString());
			} finally {
				if (reader != null) {
					reader.close();
				}

				if (connection != null) {
					connection.disconnect();
				}
			}
		}
	}


