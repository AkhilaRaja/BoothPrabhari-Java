package com.springBoot.boothPrabhari.service;

import java.util.concurrent.ExecutionException;

public interface DataExportService {

	public void exportVotersList(String boothCode) throws InterruptedException, ExecutionException;

	public void updateExportedVotersList(String boothCode) throws InterruptedException, ExecutionException;

}
