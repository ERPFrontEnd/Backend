package com.erp.blang.repositoryutils;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.data.mongodb.core.query.Criteria;

import com.erp.blang.filter.Search;
import com.erp.blang.logging.api.VCLogger;
import com.erp.blang.logging.api.impl.VCLogManager;


public class QueryUtils {


	private static VCLogger logger = VCLogManager.getLogger(QueryUtils.class);



	public static void buildQuery(Criteria criteria, List<Search> request) {



		for (Search search : request) {

			if (search.getKey().equalsIgnoreCase("orderDateTime") || search.getKey().equalsIgnoreCase("createdAt")

					|| search.getKey().equalsIgnoreCase("modifiedAt") || search.getKey().equalsIgnoreCase("orderDate")

					|| search.getKey().equalsIgnoreCase("harvestDate") ||search.getKey().equalsIgnoreCase("droneSaleDate") 

					|| search.getKey().equalsIgnoreCase("lastExecuted")||search.getKey().equalsIgnoreCase("outputSaleDate")

					|| search.getKey().equalsIgnoreCase("subscribedDate")

					|| search.getKey().equalsIgnoreCase("paymentDateTime")

					|| search.getKey().equalsIgnoreCase("promiseDispatchDate")

					|| search.getKey().equalsIgnoreCase("promiseDeliveryDate")

					|| search.getKey().equalsIgnoreCase("latestShipDate")

					|| search.getKey().equalsIgnoreCase("earliestDeliveryDate")

					|| search.getKey().equalsIgnoreCase("lastUpdateDate")

					|| search.getKey().equalsIgnoreCase("dispatchDate")

					|| search.getKey().equalsIgnoreCase("lastPayment")

					|| search.getKey().equalsIgnoreCase("uploadDateTime")

					|| search.getKey().equalsIgnoreCase("lastFinalPriceModified")

					|| search.getKey().equalsIgnoreCase("processedDateTime")

					|| search.getKey().equalsIgnoreCase("insertDateTime")

					|| search.getKey().equalsIgnoreCase("marketplaceDate")

					|| search.getKey().equalsIgnoreCase("expiryDate") || search.getKey().equalsIgnoreCase("sentDate")

					|| search.getKey().equalsIgnoreCase("receivedDate")

					|| search.getKey().equalsIgnoreCase("expectedDeliveryDate")) {

				if ("range".equals(search.getOperation())) {

					String[] str = search.getValue().toString().split("-");

					XMLGregorianCalendar gregEndDate = null;

					XMLGregorianCalendar gregStartDate = null;

					GregorianCalendar gc = new GregorianCalendar();

					gc.setTimeInMillis(Long.parseLong(str[0]));

					// to XML Gregorian Calendar

					try {

						gregStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

					} catch (DatatypeConfigurationException e) {

						logger.error("Exception in range date search - {0}", e, e.getMessage());

					}

					GregorianCalendar gcend = new GregorianCalendar();

					gcend.setTimeInMillis(Long.parseLong(str[1]));

					try {

						gregEndDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcend);

					} catch (DatatypeConfigurationException e) {

						logger.error("Exception while range date search - {0}", e, e.getMessage());

					}

					criteria.andOperator(Criteria.where(search.getKey()).gte(gregStartDate),

							Criteria.where(search.getKey()).lte(gregEndDate));

				} else if ("le".equals(search.getOperation())) {

					GregorianCalendar gcend = new GregorianCalendar();

					gcend.setTimeInMillis(Long.parseLong(search.getValue().toString()));

					XMLGregorianCalendar gregDate;

					try {

						gregDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcend);

						criteria.and(search.getKey()).lte(gregDate);

					} catch (DatatypeConfigurationException e) {

						logger.error("Exception while less than or equals date search - {0}", e, e.getMessage());

					}



				} else if ("ge".equals(search.getOperation())) {

					GregorianCalendar gcend = new GregorianCalendar();

					gcend.setTimeInMillis(Long.parseLong(search.getValue().toString()));

					XMLGregorianCalendar gregDate;

					try {

						gregDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcend);

						criteria.and(search.getKey()).gte(gregDate);

					} catch (DatatypeConfigurationException e) {

						logger.error("Exception while date search - {0}", e, e.getMessage());

					}

				} else if ("eq".equals(search.getOperation())) {

					GregorianCalendar gcend = new GregorianCalendar();

					gcend.setTimeInMillis(Long.parseLong(search.getValue().toString()));

					XMLGregorianCalendar gregDate;

					try {

						gregDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcend);

						criteria.and(search.getKey()).is(gregDate);

					} catch (DatatypeConfigurationException e) {

						logger.error("Exception while equal Date search - {0}", e, e.getMessage());

					}

				}

			} else {

				if ("gt".equals(search.getOperation())) {

					criteria.and(search.getKey()).gt(Double.parseDouble(search.getValue().toString()));

				} else if ("ge".equals(search.getOperation())) {

					criteria.and(search.getKey()).gte(Double.parseDouble(search.getValue().toString()));

				} else if ("lt".equals(search.getOperation())) {

					criteria.and(search.getKey()).lt(Double.parseDouble(search.getValue().toString()));

				} else if ("le".equals(search.getOperation())) {

					criteria.and(search.getKey()).lte(Double.parseDouble(search.getValue().toString()));

				} else if ("sw".equals(search.getOperation())) {

					criteria.and(search.getKey()).regex("^" + search.getValue(), "i");

				} else if ("ew".equals(search.getOperation())) {

					criteria.and(search.getKey()).regex(search.getValue() + "$", "i");

				} else if ("cs".equals(search.getOperation())) {

					criteria.and(search.getKey()).regex(search.getValue().toString(), "i");

				} else if ("ne".equals(search.getOperation())) {

					criteria.and(search.getKey()).ne(search.getValue());

				} else if ("nin".equals(search.getOperation())) {

					String[] s = null;

					List<String> list = null;

					if (search.getValue() != null && search.getValue().toString().contains("/")) {

						s = search.getValue().toString().split("/");

						list = Arrays.asList(s);

					}

					criteria.and(search.getKey()).nin(list);

				} else if ("in".equals(search.getOperation())) {

					String[] s = null;

					List<String> list = null;

					if (search.getValue() != null && search.getValue().toString().contains("/")) {

						s = search.getValue().toString().split("/");

						list = Arrays.asList(s);

					}

					criteria.and(search.getKey()).in(list);

				} else if ("eq".equals(search.getOperation())) {

					try {

						criteria.and(search.getKey()).is(search.getValue());

					} catch (Exception e) {

						logger.error("Exception while equal Date search - {0}", e, e.getMessage());

					}

				} else {

					criteria.and(search.getKey()).regex(search.getValue().toString(), "i");

				}

			}

		}

	}

}
