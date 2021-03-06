/*******************************************************************************
 * ===========================================================
 * Ankush : Big Data Cluster Management Solution
 * ===========================================================
 * 
 * (C) Copyright 2014, by Impetus Technologies
 * 
 * This is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License (LGPL v3) as
 * published by the Free Software Foundation;
 * 
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this software; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 ******************************************************************************/
package com.impetus.ankush.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD and query POJOs.
 * 
 * Extend this interface if you want typesafe (no casting necessary) DAO's for your domain objects.
 * 
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This is the same as lookup up all rows in a table.
     * 
     * @param start
     *            Index of first result.
     * @param maxResults
     *            Maximum number of results to return.
     * @param orderBy
     *            Property names by which results will be ordered
     * @return List of populated objects
     */
    List<T> getAll(int start, int maxResults, String... orderBy);

    /**
     * Get count of all entities of this type.
     * 
     * @return number of entities of this type.
     */
    int getAllCount();

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model classes correctly implement the
     * hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Generic method to get an object based on class and identifier. An ObjectRetrievalFailureException Runtime
     * Exception is thrown if nothing is found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Generic method to get an object based on class and identifier. An ObjectRetrievalFailureException Runtime
     * Exception is thrown if nothing is found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T getGuarded(PK id);
    
    /**
     * Generic method to get a lazily loaded reference to an object based on class and identifier. 
     * An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T getReference(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    T save(T object);

    /**
     * Generic method to delete an object based on class and id.
     *
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method to retrieve all objects that satisfy the given property->value map.
     * 
     * @param propertyValueMap
     *            the property value map
     * @param start
     *            Index of first result.
     * @param maxResults
     *            Maximum number of results to return.
     * @param orderBy
     *            Property names by which results will be ordered
     * @return all objects that match the criteria
     */
    List<T> getAllByPropertyValue(Map<String, Object> propertyValueMap, int start, int maxResults, String... orderBy);

    /**
     * Generic method to retrieve count of all objects that satisfy the given property->value map.
     * 
     * @param propertyValueMap
     *            the property value map
     * @return count of all objects that match the criteria
     */
    int getAllByPropertyValueCount(Map<String, Object> propertyValueMap);

    /**
     * Generic method to retrieve an objects that satisfy the given property->value map.
     * 
     * @param propertyValueMap
     *            the property value map
     * @return object that match the criteria
     */
    T getByPropertyValue(Map<String, Object> propertyValueMap);

    /**
     * Generic method to get all object returned by the given named query.
     *
     * @param queryName Name of the named query.
     * @param propertyValueMap the property value map * @param start Index of first result.
     * @param start the start
     * @param maxResults Maximum number of results to return.
     * @return all object returned by the given named query
     */
    List<T> getAllByNamedQuery(String queryName, Map<String, Object> propertyValueMap, int start, int maxResults);

    /**
     * Generic method to retrieve all objects that satisfy any of the property->values.
     * 
     * @param queryMap
     *            the property value map
     * @param start
     *            Index of first result.
     * @param maxResults
     *            Maximum number of results to return.
     * @param orderBy
     *            Property names by which results will be ordered
     * @return all objects that match the criteria
     */
    List<T> getAllOfOrMatch(Map<String, Object> queryMap, int start, int maxResults, String... orderBy);

    /**
     * Generic method to get count of all objects that satisfy any of the property->values.
     * 
     * @param queryMap
     *            the property value map
     * @return count of all objects that match the criteria
     */
    int getAllOfOrMatchCount(Map<String, Object> queryMap);

    /**
     * Generic method to retrieve all objects that satisfy the given criteria.
     * 
     * @param disjunctionMaps
     *            The map to create the query is disjunctive normal form. All properties is a single map are
     *            <code>anded</code> together and then all the maps are <code>ored</code> together.
     * @param start
     *            Index of first result.
     * @param maxResults
     *            Maximum number of results to return.
     * @param orderBy
     *            Property names by which results will be ordered
     * @return all objects that match the criteria
     */
    List<T> getAllByDisjunctionveNormalQuery(List<Map<String, Object>> disjunctionMaps, int start, int maxResults,
	    String... orderBy);

    /**
     * Generic method to get count of all objects that satisfy the given criteria.
     *
     * @param disjunctionMaps The map to create the query is disjunctive normal form. All properties is a single map are
     * <code>anded</code> together and then all the maps are <code>ored</code> together.
     * @return count of all objects that match the criteria
     */
    int getAllByDisjunctionveNormalQueryCount(List<Map<String, Object>> disjunctionMaps);

    /**
     * Generic method to delete all objects that satisfy the given property->value map.
     * 
     * @param propertyValueMap
     *            the property value map
     * @return number of objects that are deleted
     */
    int deleteAllByPropertyValue(Map<String, Object> propertyValueMap);
    
    /**
     * Gets the all by native query.
     *
     * @param sql the sql
     * @return the all by native query
     */
    List<T> getAllByNativeQuery(String sql);
    
    /**
     * Execute native query.
     *
     * @param sql the sql
     * @return the int
     */
    int executeNativeQuery(String sql);
}
