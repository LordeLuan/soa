package br.com.soa.interceptor;

import br.com.soa.domain.User;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.Arrays;

@PersistenceUnitExtension
public class HibernateInterceptor extends EmptyInterceptor implements Serializable {

    private static final Logger logger = Logger.getLogger(HibernateInterceptor.class);

    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        logger.info("Passei por aqui HIBERNATE");
        logger.info(entity.getClass());
        logger.info(entity);
        logger.info(id);

        for (Object ob : state) {
            System.out.println(ob);
        }

        for (String ob : propertyNames) {
            System.out.println(ob);
        }

        for (Type ty : types) {
            System.out.println(ty.getName());
        }
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) throws CallbackException {

        if ( entity instanceof User) {
            logger.info("********************AUDIT INFO START*******************");
            logger.info("Entity Name    :: " + entity.getClass());
            logger.info("Previous state :: " + Arrays.deepToString(previousState));
            logger.info("Current  state :: " + Arrays.deepToString(currentState));
            logger.info("propertyNames  :: " + Arrays.deepToString(propertyNames));
            logger.info("********************AUDIT INFO END*******************");
        }

        return super.onFlushDirty(entity,
                id,
                currentState,
                previousState,
                propertyNames,
                types);
    }

    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) throws CallbackException {

//        logger.info("Passei por aqui HIBERNATE");
//        logger.info(entity.getClass());
//        logger.info(entity);
        logger.info(id);
//        logger.info("states                          "+ Arrays.deepToString(state));

//        for(Object ob: state){
//            System.out.println(ob);
//        }

        for(String ob: propertyNames){
            System.out.println(ob);
        }

        return super.onLoad(
                entity,
                id,
                state,
                propertyNames,
                types
        );
    }
}
