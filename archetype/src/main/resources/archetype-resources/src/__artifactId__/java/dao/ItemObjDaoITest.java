#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ${package}.domain.ItemObj;
import ${package}.domain.UserObj;

import org.jboss.arquillian.container.${artifactId}.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ItemObjDaoITest
{
  @Deployment
  public static JavaArchive createDeployment()
  {
    return ShrinkWrap.create(JavaArchive.class)
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addPackages(true, new String[] { "${package}" });
  }

  @PersistenceContext
  protected EntityManager entityManager;

  @EJB
  protected ItemObjDao    itemObjDao;

  @EJB
  protected UserObjDao    userObjDao;

  @Test
  public void ${artifactId}ByOwners()
  {
    final UserObj user1 = userObjDao.create(new UserObj("user1"));
    Assert.assertNotNull(user1);

    final ItemObj itemObj = itemObjDao.create(new ItemObj("item1", user1));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByOwners(user1);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void ${artifactId}ByParticipants()
  {
    final UserObj user2 = userObjDao.create(new UserObj("user2"));
    Assert.assertNotNull(user2);

    final ItemObj itemObj = itemObjDao.create(new ItemObj("item2",
        new LinkedList<UserObj>(), user2));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByParticipants(user2);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void ${artifactId}ByUsers()
  {
    final UserObj user3 = userObjDao.create(new UserObj("user3"));
    Assert.assertNotNull(user3);

    // user3 will be the owner of ItemObj item3
    final ItemObj itemObj = itemObjDao.create(new ItemObj("item3", user3));
    Assert.assertNotNull(itemObj);
    Assert.assertNotNull(itemObj.getSummary());

    final Collection<ItemObj> result = itemObjDao.findByUsers(user3);

    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
  }
}
