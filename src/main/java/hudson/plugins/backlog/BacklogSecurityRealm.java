package hudson.plugins.backlog;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.security.AbstractPasswordBasedSecurityRealm;
import hudson.security.GroupDetails;
import hudson.security.SecurityRealm;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kohsuke.stapler.DataBoundConstructor;
import org.springframework.dao.DataAccessException;

/**
 * {@link SecurityRealm} implementation that uses Backlog users information for
 * authentication.
 * 
 * @author ikikko
 */
public class BacklogSecurityRealm extends AbstractPasswordBasedSecurityRealm {

	private static final Log LOG = LogFactory
			.getLog(BacklogSecurityRealm.class);

	@DataBoundConstructor
	public BacklogSecurityRealm() {
	}

	@Override
	protected UserDetails authenticate(String username, String password)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		if (true) {
			return new User(username, "", true, true, true, true,
					new GrantedAuthority[] { AUTHENTICATED_AUTHORITY });
		} else {
			throw new BadCredentialsException("Failed to login as " + username);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		LOG.info("Backlog Security Realm always returns the specified user : '"
				+ username + "'");
		return new User(username, "", true, true, true, true,
				new GrantedAuthority[] { AUTHENTICATED_AUTHORITY });
	}

	@Override
	public GroupDetails loadGroupByGroupname(String groupname)
			throws UsernameNotFoundException, DataAccessException {
		LOG.warn("Backlog Security Realm doesn't support groups : '"
				+ groupname + "'");
		throw new UsernameNotFoundException(groupname);
	}

	@Extension
	public static final class DescriptorImpl extends Descriptor<SecurityRealm> {
		public String getDisplayName() {
			return "Backlog";
		}

		@Override
		public String getHelpFile() {
			// TODO add valid help file
			return "/help/security/private-realm.html";
		}
	}

}
