package com.discovery.restapi.helper;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;

public class RequestResponseProd {
			protected static final String formatdebug="&format=application/json&debug=1";
			protected static final String formatdebug2="?format=application/json&debug=1";
			protected static final String DWSResource = null;
			protected static final String DWSSchema = null;
			protected static String hostaddress=null;
			public static HttpResponse response = null;
				// **************************************************************
				//following method form the url
				
				public static String FormSuperNodeUrl(String res, String sch)
				{
					String qaurle = hostaddress+"/"+res+"?schema="+sch+formatdebug; 		
					return qaurle;
					
				}
				public static String FormSuperNodeUrl_ContentOnly(String con)
				{
					String qaurle = hostaddress+"/"+con+""+formatdebug2; 
					//Reporter.log(qaurle);
					return qaurle;
					
				}
				
				
				public static String FormSuperNodeUrl_Fields(String res, String sch, String fiel)
				{
					String qaurle = hostaddress+"/"+res+"?schema="+sch+formatdebug+"&fields="+fiel; 		
					return qaurle;
					
				}
				public static String GetJSONResponse_prod(String url,String url_type)
				{
					String res = null;
					if(url_type.equalsIgnoreCase("api"))
						hostaddress = UtilMethods.GetValueFromPropFile("apihostaddress");
					else
						hostaddress = UtilMethods.GetValueFromPropFile("serviceshostaddress");	
					String urlToHit = hostaddress+""+url+"";
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpget = null;
					httpget = new HttpGet(urlToHit);
					//HttpResponse response = null;
					try {
						response = httpclient.execute(httpget);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					HttpEntity entity = response.getEntity();
					InputStream instream = null;
					if (entity != null) {
					    try {
							instream = entity.getContent();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    try {
					            try {
									res = EntityUtils.toString(entity);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    }
					    finally {
					        try {
								instream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					        //httpget.releaseConnection();
					    }
					}
					return res;
				}
		
				public static String GetJSONResponse_backup(String Resource,String Schema, String fields)
				{
					String qares = null;
					String qaurl = null;
					if(Schema.length()==0 && fields.length()==0)
					{
						qaurl= FormSuperNodeUrl_ContentOnly(Resource);
					}
					else
					qaurl = FormSuperNodeUrl_Fields(Resource,Schema,fields);
					DefaultHttpClient httpclient = new DefaultHttpClient();
					
					//HttpHost proxy1 = new HttpHost(proxyhost,port);
					//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy1);
					HttpGet httpget = null;
					httpget = new HttpGet(qaurl);
					//HttpResponse response = null;
					try {
						response = httpclient.execute(httpget);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					HttpEntity entity = response.getEntity();
					InputStream instream = null;
					if (entity != null) {
					    try {
							instream = entity.getContent();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    try {
					            try {
									qares = EntityUtils.toString(entity);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    }
					    finally {
					        try {
								instream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					        //httpget.releaseConnection();
					    }
					}
					return qares;
				}		
				
				
		
				//following method connects to qa instance and retrun JSON response for given url
				public static String GetJSONResponse_FullUrlExceptHost(String url)
				{
					String qares = null;
					hostaddress = UtilMethods.GetValueFromPropFile("hostaddress");
					//Reporter.log(hostaddress);
					String qaurl = hostaddress+""+url+""; 
					
					DefaultHttpClient httpclient = new DefaultHttpClient();
					//HttpHost proxy1 = new HttpHost(proxyhost,port);
					//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy1);
					HttpGet httpget = null;
					httpget = new HttpGet(qaurl);
					//HttpResponse response = null;
					try {
						response = httpclient.execute(httpget);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					HttpEntity entity = response.getEntity();
					InputStream instream = null;
					if (entity != null) {
					    try {
							instream = entity.getContent();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    try {
					            try {
									qares = EntityUtils.toString(entity);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    }
					    finally {
					        try {
								instream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					        //httpget.releaseConnection();
					    }
					}
					return qares;
				}		
}
