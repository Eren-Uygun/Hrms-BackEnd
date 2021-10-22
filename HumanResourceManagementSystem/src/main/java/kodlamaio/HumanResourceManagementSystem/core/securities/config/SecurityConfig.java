package kodlamaio.HumanResourceManagementSystem.core.securities.config;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.securities.filter.CustomAuthorizationFilter;
import kodlamaio.HumanResourceManagementSystem.core.securities.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtFilter jwtFilter;
    private final UserService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean(name = "passwordEncoderBean")
    @Deprecated()
    public PasswordEncoder passwordEncoder()
    {
      return new BCryptPasswordEncoder();

       // return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests()
                //http.csrf().disable().authorizeRequests()
                .antMatchers("/api/auths/login/**").permitAll()
                .antMatchers("/api/candidates/add").permitAll() //Register Sayfası
                .antMatchers("/api/employers/add").permitAll() //Register Sayfası
                .antMatchers("/api/curriculumVitaes/**").permitAll()
                .antMatchers("/api/employers/**").permitAll()
                .antMatchers("/api/hrmsEmployees/**").permitAll()
                .antMatchers("/api/foreignLanguages/**").permitAll()
                .antMatchers("/api/jobExperiences/**").permitAll()
                .antMatchers("/api/educations/**").permitAll()
                .antMatchers("/api/skills/**").permitAll()
                .antMatchers("/api/coverLetters/**").permitAll()
                .antMatchers("/api/jobAdvertisements/**").permitAll()
                .antMatchers("/api/jobs/**").permitAll()
                .antMatchers("/api/cities/**").permitAll()
                .antMatchers("/api/jobTypes/**").permitAll()
                .antMatchers("/api/workPlaces/**").permitAll()
                .antMatchers("/api/favoriteJobAdvertisement/**").permitAll()




                /*
                .antMatchers("/api/curriculumVitaes/**").hasAnyAuthority("ROLE_CANDIDATE")
                .antMatchers("/api/foreignLanguages/**").hasAnyAuthority("ROLE_CANDIDATE","ROLE_EMPLOYEE")
                .antMatchers("/api/jobExperiences/**").hasAnyAuthority("ROLE_CANDIDATE","ROLE_EMPLOYEE")
                .antMatchers("/api/educations/**").hasAnyAuthority("ROLE_CANDIDATE","ROLE_EMPLOYEE")
                .antMatchers("/api/skills/**").hasAnyAuthority("ROLE_CANDIDATE","ROLE_EMPLOYEE")
                .antMatchers("/api/coverLetters/**").hasAnyAuthority("ROLE_CANDIDATE","ROLE_EMPLOYEE")


                .antMatchers("/api/jobAdvertisements/**").hasAnyAuthority("ROLE_EMPLOYER","ROLE_EMPLOYEE")

                .antMatchers("/api/jobs/**").hasAnyAuthority("ROLE_EMPLOYER","ROLE_EMPLOYEE")
                .antMatchers("/api/cities/**").hasAnyAuthority("ROLE_EMPLOYER","ROLE_EMPLOYEE")
                .antMatchers("/api/jobTypes/**").hasAnyAuthority("ROLE_EMPLOYER","ROLE_EMPLOYEE")
                .antMatchers("/api/workPlaces/**").hasAnyAuthority("ROLE_EMPLOYER","ROLE_EMPLOYEE")

                .antMatchers("/api").permitAll()
                .antMatchers("/api/activations/**").permitAll()
                //.hasAnyAuthority("ROLE_CANDIDATE")
                /*.antMatchers("/api/register/job-seeker").permitAll()
                .antMatchers("/api/register/job-seeker/confirm").permitAll()
                .antMatchers("/api/register/employer").permitAll()
                .antMatchers("/api/register/employer/confirm").permitAll()*/
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
