/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avro.events;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PullRequestCreated extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2562039015628979295L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PullRequestCreated\",\"namespace\":\"avro.events\",\"fields\":[{\"name\":\"number\",\"type\":\"long\"},{\"name\":\"merged\",\"type\":\"boolean\"},{\"name\":\"title\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"user\",\"type\":{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"username\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"url\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"avatarUrl\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
   private long number;
   private boolean merged;
   private java.lang.String title;
   private avro.events.User user;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PullRequestCreated() {}

  /**
   * All-args constructor.
   * @param number The new value for number
   * @param merged The new value for merged
   * @param title The new value for title
   * @param user The new value for user
   */
  public PullRequestCreated(java.lang.Long number, java.lang.Boolean merged, java.lang.String title, avro.events.User user) {
    this.number = number;
    this.merged = merged;
    this.title = title;
    this.user = user;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return number;
    case 1: return merged;
    case 2: return title;
    case 3: return user;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: number = (java.lang.Long)value$; break;
    case 1: merged = (java.lang.Boolean)value$; break;
    case 2: title = (java.lang.String)value$; break;
    case 3: user = (avro.events.User)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'number' field.
   * @return The value of the 'number' field.
   */
  public java.lang.Long getNumber() {
    return number;
  }

  /**
   * Sets the value of the 'number' field.
   * @param value the value to set.
   */
  public void setNumber(java.lang.Long value) {
    this.number = value;
  }

  /**
   * Gets the value of the 'merged' field.
   * @return The value of the 'merged' field.
   */
  public java.lang.Boolean getMerged() {
    return merged;
  }

  /**
   * Sets the value of the 'merged' field.
   * @param value the value to set.
   */
  public void setMerged(java.lang.Boolean value) {
    this.merged = value;
  }

  /**
   * Gets the value of the 'title' field.
   * @return The value of the 'title' field.
   */
  public java.lang.String getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * @param value the value to set.
   */
  public void setTitle(java.lang.String value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'user' field.
   * @return The value of the 'user' field.
   */
  public avro.events.User getUser() {
    return user;
  }

  /**
   * Sets the value of the 'user' field.
   * @param value the value to set.
   */
  public void setUser(avro.events.User value) {
    this.user = value;
  }

  /**
   * Creates a new PullRequestCreated RecordBuilder.
   * @return A new PullRequestCreated RecordBuilder
   */
  public static avro.events.PullRequestCreated.Builder newBuilder() {
    return new avro.events.PullRequestCreated.Builder();
  }

  /**
   * Creates a new PullRequestCreated RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PullRequestCreated RecordBuilder
   */
  public static avro.events.PullRequestCreated.Builder newBuilder(avro.events.PullRequestCreated.Builder other) {
    return new avro.events.PullRequestCreated.Builder(other);
  }

  /**
   * Creates a new PullRequestCreated RecordBuilder by copying an existing PullRequestCreated instance.
   * @param other The existing instance to copy.
   * @return A new PullRequestCreated RecordBuilder
   */
  public static avro.events.PullRequestCreated.Builder newBuilder(avro.events.PullRequestCreated other) {
    return new avro.events.PullRequestCreated.Builder(other);
  }

  /**
   * RecordBuilder for PullRequestCreated instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PullRequestCreated>
    implements org.apache.avro.data.RecordBuilder<PullRequestCreated> {

    private long number;
    private boolean merged;
    private java.lang.String title;
    private avro.events.User user;
    private avro.events.User.Builder userBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avro.events.PullRequestCreated.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.number)) {
        this.number = data().deepCopy(fields()[0].schema(), other.number);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.merged)) {
        this.merged = data().deepCopy(fields()[1].schema(), other.merged);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.title)) {
        this.title = data().deepCopy(fields()[2].schema(), other.title);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.user)) {
        this.user = data().deepCopy(fields()[3].schema(), other.user);
        fieldSetFlags()[3] = true;
      }
      if (other.hasUserBuilder()) {
        this.userBuilder = avro.events.User.newBuilder(other.getUserBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing PullRequestCreated instance
     * @param other The existing instance to copy.
     */
    private Builder(avro.events.PullRequestCreated other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.number)) {
        this.number = data().deepCopy(fields()[0].schema(), other.number);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.merged)) {
        this.merged = data().deepCopy(fields()[1].schema(), other.merged);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.title)) {
        this.title = data().deepCopy(fields()[2].schema(), other.title);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.user)) {
        this.user = data().deepCopy(fields()[3].schema(), other.user);
        fieldSetFlags()[3] = true;
      }
      this.userBuilder = null;
    }

    /**
      * Gets the value of the 'number' field.
      * @return The value.
      */
    public java.lang.Long getNumber() {
      return number;
    }

    /**
      * Sets the value of the 'number' field.
      * @param value The value of 'number'.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder setNumber(long value) {
      validate(fields()[0], value);
      this.number = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'number' field has been set.
      * @return True if the 'number' field has been set, false otherwise.
      */
    public boolean hasNumber() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'number' field.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder clearNumber() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'merged' field.
      * @return The value.
      */
    public java.lang.Boolean getMerged() {
      return merged;
    }

    /**
      * Sets the value of the 'merged' field.
      * @param value The value of 'merged'.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder setMerged(boolean value) {
      validate(fields()[1], value);
      this.merged = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'merged' field has been set.
      * @return True if the 'merged' field has been set, false otherwise.
      */
    public boolean hasMerged() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'merged' field.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder clearMerged() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * @return The value.
      */
    public java.lang.String getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * @param value The value of 'title'.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder setTitle(java.lang.String value) {
      validate(fields()[2], value);
      this.title = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'title' field.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder clearTitle() {
      title = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'user' field.
      * @return The value.
      */
    public avro.events.User getUser() {
      return user;
    }

    /**
      * Sets the value of the 'user' field.
      * @param value The value of 'user'.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder setUser(avro.events.User value) {
      validate(fields()[3], value);
      this.userBuilder = null;
      this.user = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'user' field has been set.
      * @return True if the 'user' field has been set, false otherwise.
      */
    public boolean hasUser() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'user' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public avro.events.User.Builder getUserBuilder() {
      if (userBuilder == null) {
        if (hasUser()) {
          setUserBuilder(avro.events.User.newBuilder(user));
        } else {
          setUserBuilder(avro.events.User.newBuilder());
        }
      }
      return userBuilder;
    }

    /**
     * Sets the Builder instance for the 'user' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public avro.events.PullRequestCreated.Builder setUserBuilder(avro.events.User.Builder value) {
      clearUser();
      userBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'user' field has an active Builder instance
     * @return True if the 'user' field has an active Builder instance
     */
    public boolean hasUserBuilder() {
      return userBuilder != null;
    }

    /**
      * Clears the value of the 'user' field.
      * @return This builder.
      */
    public avro.events.PullRequestCreated.Builder clearUser() {
      user = null;
      userBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public PullRequestCreated build() {
      try {
        PullRequestCreated record = new PullRequestCreated();
        record.number = fieldSetFlags()[0] ? this.number : (java.lang.Long) defaultValue(fields()[0]);
        record.merged = fieldSetFlags()[1] ? this.merged : (java.lang.Boolean) defaultValue(fields()[1]);
        record.title = fieldSetFlags()[2] ? this.title : (java.lang.String) defaultValue(fields()[2]);
        if (userBuilder != null) {
          record.user = this.userBuilder.build();
        } else {
          record.user = fieldSetFlags()[3] ? this.user : (avro.events.User) defaultValue(fields()[3]);
        }
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
