#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import javax.ejb.Stateless;

import ${package}.domain.UserObj;

@Stateless
public class UserObjDao extends GenericEntityDao<UserObj>
{

}
