/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.reference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.HomePage;
import org.openmrs.uitestframework.test.TestBase;

public class LoginTest extends TestBase {
    private HomePage homePage;

    @Test
    @Category(org.openmrs.reference.groups.BuildTests.class)
    public void verifyModulesAvailableOnHomePage() throws Exception {
    	loginPage.loginAsAdmin();
    	homePage = new HomePage(driver);
        assertPage(homePage);
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
//        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(10));
    }
    @Test
    public void verifyClerkModulesAvailableOnHomePage() throws Exception {
    	loginPage.loginAsClerk();
        homePage = new HomePage(driver);
    	assertPage(homePage);
    	assertTrue(homePage.isActiveVisitsAppPresent());
    	assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(4));
    }
    @Test
    public void verifyDoctorModulesAvailableOnHomePage() throws Exception {
    	loginPage.login("doctor", "Doctor123");
        homePage = new HomePage(driver);
    	assertPage(homePage);
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(3));
    }
    @Test
    public void verifyNurseModulesAvailableOnHomePage() throws Exception {
    	loginPage.loginAsNurse();
        homePage = new HomePage(driver);
    	assertPage(homePage);
    	assertTrue(homePage.isFindAPatientAppPresent());
    	assertTrue(homePage.isActiveVisitsAppPresent());
    	assertTrue(homePage.isCaptureVitalsAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(4));
    }
    @Test
    public void verifySysadminModulesAvailableOnHomePage() throws Exception {
        loginPage.loginAsSysadmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
//        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(2));
    }

}
