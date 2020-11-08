TODO: Add name strategy

## ERRORS:

`alter table if exists role add column securityLevel int4 not null;`
[23502] ERROR: column "securitylevel" contains null values

Solution: Add annotation
 * From: `val securityLevel: Int = 0,`
 * To: `@Column(name = "security_level") val securityLevel: Int = 0,`

---

Error executing DDL "alter table if exists daily_reload_token add column keepSessionDaily boolean"
 * From: `@Column(name = "keepSessionDaily") val keepSessionDaily: Boolean = false,`
 * To: `@Column(name = "keep_session_daily") val keepSessionDaily: Boolean = false,`


