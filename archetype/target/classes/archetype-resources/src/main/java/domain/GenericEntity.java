#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericEntity
{
  /**
   * This is required for various persistence mechanisms.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @Version
  private Long version;

  /**
   * Required for JPA.
   * 
   * @see javax.persistence.Id
   */
  public Long getId()
  {
    return id;
  }

  /**
   * Required for JPA.
   * 
   * @see javax.persistence.Version
   */
  public Long getVersion()
  {
    return version;
  }

  /**
   * Required for JPA.
   * 
   * @see javax.persistence.Id
   */
  public void setId(final Long id)
  {
    this.id = id;
  }

  /**
   * Required for JPA.
   * 
   * @see javax.persistence.Version
   */
  public void setVersion(final Long version)
  {
    this.version = version;
  }
}
