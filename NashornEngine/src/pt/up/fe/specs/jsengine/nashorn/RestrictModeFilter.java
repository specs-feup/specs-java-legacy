/**
 * Copyright 2018 SPeCS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.jsengine.nashorn;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jdk.nashorn.api.scripting.ClassFilter;

/**
 * @deprecated Uses Nashorn classes
 * @author JoaoBispo
 *
 */
@Deprecated
public class RestrictModeFilter implements ClassFilter {

    private final Set<String> blacklistedClasses;

    public RestrictModeFilter(Collection<Class<?>> blacklistedClasses) {
        this.blacklistedClasses = buildForbiddenClasses(blacklistedClasses);
    }

    // TODO: Put LARASystem.class eventually
    // private static final Set<String> FORBIDDEN_CLASSES = buildForbiddenClasses(ProcessBuilder.class,
    // LaraSystemTools.class);

    @Override
    public boolean exposeToScripts(String classname) {
        // System.out.println("CLASS NAME:" + classname);
        if (blacklistedClasses.contains(classname)) {
            return false;
        }
        // System.out.println("PASSED");
        return true;
    }

    private static Set<String> buildForbiddenClasses(Collection<Class<?>> classes) {
        Set<String> forbiddenClasses = new HashSet<>();

        for (Class<?> aClass : classes) {
            forbiddenClasses.add(aClass.getName());
        }
        // System.out.println("FORBIDDEN CLASSES:" + forbiddenClasses);
        return forbiddenClasses;
    }

}
