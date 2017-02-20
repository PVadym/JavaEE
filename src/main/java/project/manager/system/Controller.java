package project.manager.system;

import project.manager.system.jbdc.DeveloperDao;
import project.manager.system.jbdc.SkillDao;

/**
 * Created by Вадим on 18.02.2017.
 */
public class Controller {
    private DeveloperDao developerDao;
    private SkillDao skillDao;

    public Controller() {
        developerDao = new DeveloperDao();
        skillDao = new SkillDao();
    }

    public DeveloperDao getDeveloperDao() {
        return developerDao;
    }

    public SkillDao getSkillDao() {
        return skillDao;
    }
}
