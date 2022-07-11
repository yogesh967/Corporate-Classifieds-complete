package com.cts.offermicroservice.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import com.cts.offermicroservice.model.Offer;
import com.cts.offermicroservice.model.OfferCategory;

@Service
public interface OfferService {

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid, employee can save the offer in the database 
	 * @param token
	 * @param offer
	 * @return offer
	 * @throws InvalidUserException**/
	public Offer addOffer(String token, Offer offer);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list  by using offer id
	 * @param token
	 * @param offerid
	 * @return offer
	 * @throws InvalidUserException**/
	public Offer viewOffer(String token, int offerid);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list  by using the offer posted date
	 * If the date is not in the given format it will throw parse exception
	 *If not it will returns all the offers based on the posted date
	 * @param token
	 * @param date
	 * @return offer
	 * @throws 
	 * @throws InvalidUserException**/
	public List<Offer> getOfferByPostedDate(String token, String date) throws ParseException;

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list  by using the offer Category Id
	 * If the category is not found it throws category not found exception
	 * If not it will returns all the offers based on the Category
	 * @param token
	 * @param categoryid
	 * @return offer
	 * @throws InvalidUserException
	 * @throws CategoryNotFoundException**/
	public List<Offer> getOfferByCategory(String token, int catid);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list in descending order by using jpql query based on the likes 
	 * It will returns the top three offers by using filters in the stream
	 * @param token
	 * @return offer
	 * @throws InvalidUserException**/
	public List<Offer> getOfferByTopLikes(String token);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list by using employee id
	 * It will return all the offers in the employee id 
	 * @param  token
	 * @param  empid
	 * @return offer
	 * @throws InvalidUserException**/
	public List<Offer> getOfferByEmpId(String token, int empid);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list of the employee by using offer id
	 * It will update the like of the employee in the offer list by 1
	 * @param token
	 * @param offerid
	 * @param empid
	 * @return offer
	 * @throws InvalidUserException**/
	public Offer updateOffer(String token, int offerid, int empid);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets the offer list 
	 * It will return all the offers posted by all the employees
	 * @param  token
	 * @return offer
	 * @throws InvalidUserException**/
	public List<Offer> viewAllOffers(String token);

	/**Checks the validity of the token.If not valid,it throws InvalidUserException.
	 * If the token is valid,it gets category  by using Category Id
	 * If the category is not found it throws category not found exception
	 * If not it will returns the  category
	 * @param token
	 * @param categoryid
	 * @return category
	 * @throws InvalidUserException
	 * @throws CategoryNotFoundException**/
	public OfferCategory getCategory(String token, int catid);

}
